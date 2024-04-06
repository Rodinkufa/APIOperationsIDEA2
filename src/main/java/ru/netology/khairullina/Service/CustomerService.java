package ru.netology.khairullina.Service;

import jakarta.annotation.PostConstruct;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import ru.netology.khairullina.Model.Customer;

import java.util.List;
import java.util.Scanner;

@Service
@Scope("singleton")
public class CustomerService {

    private final StorageService<Customer> storage = new StorageService<>();

    public CustomerService () {
    }

    @PostConstruct
    public void initStorage() {
        storage.Add(new Customer(1, "Вася", "vasja@gmail.com"));
        storage.Add(new Customer(2, "Петя", "peja@gmail.com"));
        storage.Add(new Customer(3, "Наташа", "natasha@gmail.com"));
        storage.Add(new Customer(4, "Гюльчитай", "gultshitay@gmail.com"));
    }

    // добавляем клиента по параметрам
    public void addCustomer(int id, String name, String email) {
        Customer customer = new Customer();
        customer.setId(id);
        customer.setName(name);
        customer.setEmail(email);
        addCustomer(customer);
    }

    // добавляем клиента по объекту
    public void addCustomer(Customer customer) {
        storage.Add(customer);
    }

    public List<Customer> getCustomers() {
        return storage.GetList();
    }

    // поиск клиента по ид
    public Customer getCustomer(int id) {

        List<Customer> customers = storage.GetList();

        if (customers.isEmpty()) {
            return null;
        }

        for(Customer customer : customers) {
            if(customer.getId() == id) {
                return customer;
            }
        }
        return null;
    }

    // вводим нового клиента
    public Customer CreateCustomer() {

        Scanner scanner = new Scanner(System.in);

        System.out.println("Введите информацию о клиенте №" + (storage.GetList().size() + 1));

        System.out.print("ID клиента: ");
        int id = (Integer.parseInt(scanner.nextLine()));

        System.out.print("Имя клиента: ");
        String name = scanner.nextLine();

        System.out.print("Email клиента: ");
        String email = scanner.nextLine();

        scanner.close();

        return new Customer(id, name, email);
    }

    public boolean updateCustomer(Customer customer) {

        Customer findCustomer = getCustomer(customer.getId());

        if (findCustomer == null) {
            return false;
        }

        findCustomer.setName(customer.getName());
        findCustomer.setEmail(customer.getEmail());
        return true;
    }

    public boolean deleteCustomer(Integer customerId) {

        Customer findCustomer = getCustomer(customerId);

        if (findCustomer == null) {
            return false;
        }

        storage.Delete(findCustomer);
        return true;
    }
}
