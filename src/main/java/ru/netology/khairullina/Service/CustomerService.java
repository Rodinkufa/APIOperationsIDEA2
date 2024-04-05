package ru.netology.khairullina.Service;

import ru.netology.khairullina.Model.Customer;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class CustomerService {

    private final List<Customer> customers = new ArrayList<>();

    public CustomerService () {

    }

    // добавляем клиента по параметрам
    public void AddCustomer(int id, String name, String email) {
        Customer customer;
        customer = new Customer();
        customer.setId(id);
        customer.setName(name);
        customer.setEmail(email);
        AddCustomer(customer);
    }

    // добавляем клиента по объекту
    public void AddCustomer(Customer customer) {
        customers.add(customer);
    }


    // поиск клиента по ид
    public Customer FindCustomer(int id) {

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

        System.out.println("Введите информацию о клиенте №" + (customers.size() + 1));

        System.out.print("ID клиента: ");
        int id = (Integer.parseInt(scanner.nextLine()));

        System.out.print("Имя клиента: ");
        String name = scanner.nextLine();

        System.out.print("Email клиента: ");
        String email = scanner.nextLine();

        return new Customer(id, name, email);  //AddCustomer(id, name, email);
    }

}
