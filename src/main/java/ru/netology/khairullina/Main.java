package ru.netology.khairullina;

import ru.netology.khairullina.pack1.*;
import ru.netology.khairullina.pack1.OperationException.CustomerOperationOutOfBoundException;

import java.io.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {
   // public static int customerId = 0;
   // public static int operationId = 0;
    public static Scanner scanner = new Scanner(System.in);

    public static DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    public static Operation[] operations = new Operation[2];
    public static Customer[] customers = new Customer[2];
    //public static int[][] statement;

    //static int operationCounter = 0;

    public static OperationData data = new OperationData();

    public static void AddClient() {

        for (int i = 0; i < customers.length; i++) {
            System.out.println("Введите информацию о клиенте №" + (i + 1));

            System.out.print("ID клиента: ");
            data.setCustomerId(Integer.parseInt(scanner.nextLine()));

            System.out.print("Имя клиента: ");
            String name = scanner.nextLine();

            System.out.print("Email клиента: ");
            String email = scanner.nextLine();

            customers[i] = new Customer(data.getCustomerId(), name, email);
        }
        System.out.println(data);

    }

    public static void AddTransaction() {
        try {
            for (int i = 0; i < operations.length; i++) {

                System.out.println("Введите информацию о транзакции №" + (i + 1));

                System.out.print("ID транзакции: ");
                data.setOperationId(Integer.parseInt(scanner.nextLine()));

                System.out.print("Тип транзакции: ");
                String type = scanner.nextLine();

                System.out.print("Сумма транзакции: ");
                double amount = Double.parseDouble(scanner.nextLine());

                System.out.print("Дата транзакции (в формате ГГГГ-ММ-ДД): ");
                LocalDate date = LocalDate.parse(scanner.nextLine(), formatter);

                operations[i] = new Operation(data.getOperationId(),type,amount,date, data.getCustomerId());

                boolean clientFound = false;
                while (! clientFound) {
                    System.out.print("ID клиента для операции: ");
                    data.setCustomerId(Integer.parseInt(scanner.nextLine()));

                    for (int j = 0; j < customers.length; j++) {
                        if (customers[j] != null && customers[j].getId() == data.getCustomerId()) {
                            clientFound = true;
                            break;
                        }
                    }

                    if (! clientFound) {
                        System.out.println("Клиент с указанным ID не найден.");
                        System.out.println("Хотите создать нового клиента или изменить выбор? (создать/изменить)");
                        String choice = scanner.nextLine();
                        if (choice.equalsIgnoreCase("создать")) {
                            System.out.print("Введите имя нового клиента: ");
                            String name = scanner.nextLine();

                            System.out.print("Введите email нового клиента: ");
                            String email = scanner.nextLine();

                            Customer[] newCustomers = new Customer[customers.length + 1];
                            System.arraycopy(customers, 0, newCustomers, 0, customers.length);
                            newCustomers[customers.length] = new Customer(data.getCustomerId(), name, email);
                            customers = newCustomers;
                            clientFound = true;
                        } else if (choice.equalsIgnoreCase("изменить")) {
                            // Повторный запрос ID клиента
                        }
                    }
                }

                if (type.equalsIgnoreCase("cashback")) {
                    System.out.print("Сумма кэшбэка: ");
                    double cashbackAmount = Double.parseDouble(scanner.nextLine());
                    operations[i] = new CashbackOperation(data.getOperationId(), type, amount, date, data.getCustomerId(), cashbackAmount);
                } else if (type.equalsIgnoreCase("loan")) {
                    System.out.print("Проценты по кредиту: ");
                    double interestRate = Double.parseDouble(scanner.nextLine());
                    operations[i] = new LoanOperation(data.getOperationId(), type, amount, date, data.getCustomerId(), interestRate);
                }
                data.setStatement(new int[customers.length][operations.length]);
                saveOperationToStatement(data.getOperationId(), data.getCustomerId());
            }
        } catch (RuntimeException e) {
            try {
                throw new CustomerOperationOutOfBoundException(data.getOperationId(), data.getCustomerId());
            } catch (CustomerOperationOutOfBoundException ex) {
                throw new RuntimeException(ex);
            }
        }
    }

    public static Operation[] getOperations(int clientId) {
        ArrayList<Operation> clientOperations = new ArrayList<>();

        for (Operation operation : operations) {
            if (operation != null && operation.getCustomerId() == clientId) {
                clientOperations.add(operation);
            }
        }

        return clientOperations.toArray(new Operation[0]);
    }

    public static void printOperations(Operation[] operations) {
        if (operations.length == 0) {
            System.out.println("У данного клиента нет операций.");
            return;
        }

        for (Operation operation : operations) {
            operation.print();
        }
    }

    public static void saveOperationToStatement(int operationId, int customerId) {
        for (int j = 0; j < customers.length; j++) {
            if (customers[j] != null && customers[j].getId() == customerId) {
                for (int k = 0; k < operations.length; k++) {
                    if (data.getStatement()[j][k] == 0) {
                        data.getStatement()[j][k] = operationId;
                        return;
                    }
                }
                System.out.println("Ошибка: не удалось сохранить операцию. Нет места в выписке.");
                return;
            }
        }
        System.out.println("Ошибка: клиент с ID " + customerId + " не найден.");
    }

    public static void main(String[] args) {
        AddClient();
        AddTransaction();

        for (int i = 0; i < customers.length; i++) {
            for (int j = 0; j < operations.length; j++) {
                data.getStatement()[i][j] = 0;
            }
        }

        for (int i = 0; i < operations.length; i++) {
            data.setCustomerId(operations[i].getCustomerId());
            data.setOperationId(operations[i].getId());

            for (int j = 0; j < customers.length; j++) {
                if (customers[j] != null && customers[j].getId() == data.getCustomerId()) {
                    for (int k = 0; k < operations.length; k++) {
                        if (data.getStatement()[j][k] == 0) {
                            data.setStatement([data.getCustomerId()][data.getOperationId()]);
                            break;
                        }
                    }
                    break;
                }
            }
        }

        for (Operation operation : operations) {
            operation.print();
        }

        System.out.println("Массив statement:");
        for (int i = 0; i < customers.length; i++) {
            System.out.print("Клиент " + (i + 1) + ": ");
            for (int j = 0; j < operations.length; j++) {
                if (statement[i][j] != 0) {
                    System.out.print(statement[i][j] + " ");
                }
            }
            System.out.println();
        }

        System.out.print("Введите ID клиента, чтобы получить его операции: ");
        int clientId = Integer.parseInt(scanner.nextLine());
        Operation[] clientOperations = getOperations(clientId);
        System.out.println("Операции клиента с ID " + clientId + ":");
        printOperations(clientOperations);

        scanner.close();
    }
}
