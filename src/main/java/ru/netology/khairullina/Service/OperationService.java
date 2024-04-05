package ru.netology.khairullina.Service;

import ru.netology.khairullina.Model.*;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import static ru.netology.khairullina.Main.cs;

public class OperationService {

    private final List<Operation> operations = new ArrayList<>();
    public OperationService () {

    }

    // добавляем операцию по параметрам
    public void AddOperation(int id, String type, double amount, LocalDate date, int customerId) {
        Operation operation;
        operation = new Operation();
        operation.setId(id);
        operation.setType(type);
        operation.setAmount(amount);
        operation.setDate(date);
        operation.setCustomerId(customerId); // Инициализация поля ID клиента
        AddOperation(operation);
    }

    // добавляем операцию по объекту
    public void AddOperation(Operation operation) {
        operations.add(operation);
    }

    // возврат операций по клиенту
    public  ArrayList<Operation> getOperations(int customerId) {

        ArrayList<Operation> customerOperations = new ArrayList<>();

        for (Operation operation : operations) {
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

        System.out.println("Введите информацию о транзакции №" + (operations.size() + 1));

        System.out.print("ID транзакции: ");
        int id  = Integer.parseInt(scanner.nextLine());

        System.out.print("Тип транзакции: ");
        String type = scanner.nextLine();

        System.out.print("Сумма транзакции: ");
        double amount = Double.parseDouble(scanner.nextLine());

        System.out.print("Дата транзакции (в формате ГГГГ-ММ-ДД): ");
        LocalDate date = LocalDate.parse(scanner.nextLine(), formatter);

        // объявляем переменную для подбора клиента
        Customer findCustomer = null;
        do {
            // бегаем по кругу пока не найдем или не создадим клиента
            // или пока не упадем с ошибкой :(((((
            System.out.print("Введите ID клиента: ");
            int customerId = Integer.parseInt(scanner.nextLine());
            findCustomer = cs.FindCustomer(customerId);
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
