package ru.netology.khairullina.Service;

import jakarta.annotation.PostConstruct;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import ru.netology.khairullina.Model.*;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


@Service
@Scope("singleton")
public class OperationService {

    private final StorageService<Operation> storage;
    public OperationService (StorageService<Operation> storage) {
        this.storage = storage;
    }

    @PostConstruct
    public void initStorage() {
        storage.Add(new Operation(1, "normal", 123456, LocalDate.now(), 1));
        storage.Add(new Operation(2, "normal", 987654, LocalDate.now(), 1));
        storage.Add(new Operation(3, "normal", 159753, LocalDate.now(), 2));
        storage.Add(new Operation(4, "normal", 951753, LocalDate.now(), 4));
    }

    // добавляем операцию по параметрам
    public void AddOperation(int id, String type, double amount, LocalDate date, int customerId) {
        Operation operation = new Operation();
        operation.setId(id);
        operation.setType(type);
        operation.setAmount(amount);
        operation.setDate(date);
        operation.setCustomerId(customerId); // Инициализация поля ID клиента
        AddOperation(operation);
    }

    // добавляем операцию по объекту
    public void AddOperation(Operation operation) {
        storage.Add(operation);
    }

    // возврат операций по клиенту
    public  ArrayList<Operation> getOperations(int customerId) {

        ArrayList<Operation> customerOperations = new ArrayList<>();

        for (Operation operation : storage.GetList()) {
            if (operation != null && operation.getCustomerId() == customerId) {
                customerOperations.add(operation);
            }
        }

        return customerOperations;
    }

    // печать списка операций по клиенту
    public void printOperations(int customerId) {

        ArrayList<Operation> customerOperations = getOperations(customerId);

        if (customerOperations.isEmpty()) {
            System.out.println("У данного клиента нет операций.");
            return;
        }

        for (Operation operation : customerOperations) {
            operation.print();
        }
    }

    // вводим новую операцию
    public Operation CreateOperation() {

        Scanner scanner = new Scanner(System.in);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

        System.out.println("Введите информацию о транзакции №" + (storage.GetList().size() + 1));

        System.out.print("ID транзакции: ");
        int id  = Integer.parseInt(scanner.nextLine());

        System.out.print("Тип транзакции: ");
        String type = scanner.nextLine();

        System.out.print("Сумма транзакции: ");
        double amount = Double.parseDouble(scanner.nextLine());

        System.out.print("Дата транзакции (в формате ГГГГ-ММ-ДД): ");
        LocalDate date = LocalDate.parse(scanner.nextLine(), formatter);

        CustomerService cs = new CustomerService() ;

        // объявляем переменную для подбора клиента
        Customer findCustomer = null;
        do {
            System.out.print("Введите ID клиента: ");
            int customerId = Integer.parseInt(scanner.nextLine());
            findCustomer = cs.getCustomer(customerId);
            if (findCustomer == null) {
                System.out.println("Клиент с указанным ID не найден.");
                System.out.println("Хотите создать нового клиента или изменить выбор? ([C}оздать/[И]зменить/[О]тменить)");
                String choice = scanner.nextLine();
                if (choice.equalsIgnoreCase("создать") || choice.equalsIgnoreCase("с")) {
                    findCustomer = cs.CreateCustomer();
                }
                if (choice.equalsIgnoreCase("отменить") || choice.equalsIgnoreCase("о")) {
                    break;
                }
            }
        } while(findCustomer == null);

        if (findCustomer == null)
            throw new OperationException.OperationRuntimeException();

        if (type.equalsIgnoreCase("cashback")) {
            System.out.print("Сумма кэшбэка: ");
            double cashbackAmount = Double.parseDouble(scanner.nextLine());
            return new CashbackOperation(id, type, amount, date, findCustomer.getId(), cashbackAmount);
        } else if (type.equalsIgnoreCase("loan")) {
            System.out.print("Проценты по кредиту: ");
            double interestRate = Double.parseDouble(scanner.nextLine());
            return new LoanOperation(id, type, amount, date, findCustomer.getId(), interestRate);
        } else {
            return new Operation(id, type, amount, date, findCustomer.getId());
        }
    }
}
