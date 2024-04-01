package ru.netology.khairullina;

import ru.netology.khairullina.pack1.*;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    static Operation[] operations = new Operation[5];
    static Customer[] customers = new Customer[5];
    static int[][] statement;
    static int operationCounter = 0;

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
                    if (statement[j][k] == 0) {
                        statement[j][k] = operationId;
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
        Scanner scanner = new Scanner(System.in);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

        for (int i = 0; i < customers.length; i++) {
            System.out.println("Введите информацию о клиенте №" + (i + 1));

            System.out.print("ID клиента: ");
            int id = Integer.parseInt(scanner.nextLine());

            System.out.print("Имя клиента: ");
            String name = scanner.nextLine();

            System.out.print("Email клиента: ");
            String email = scanner.nextLine();

            customers[i] = new Customer(id, name, email);
        }

        for (int i = 0; i < operations.length; i++) {
            operations[i] = new Operation();

            System.out.println("Введите информацию о транзакции №" + (i + 1));

            System.out.print("ID транзакции: ");
            int id = Integer.parseInt(scanner.nextLine());
            operations[i].setId(id);

            System.out.print("Тип транзакции: ");
            String type = scanner.nextLine();
            operations[i].setType(type);

            System.out.print("Сумма транзакции: ");
            double amount = Double.parseDouble(scanner.nextLine());
            operations[i].setAmount(amount);

            System.out.print("Дата транзакции (в формате ГГГГ-ММ-ДД): ");
            LocalDate date = LocalDate.parse(scanner.nextLine(), formatter);
            operations[i].setDate(date);

            int customerId = 0;
            boolean clientFound = false;
            while (!clientFound) {
                System.out.print("ID клиента для операции: ");
                customerId = Integer.parseInt(scanner.nextLine());

                for (int j = 0; j < customers.length; j++) {
                    if (customers[j] != null && customers[j].getId() == customerId) {
                        clientFound = true;
                        break;
                    }
                }

                if (!clientFound) {
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
                        newCustomers[customers.length] = new Customer(customerId, name, email);
                        customers = newCustomers;
                        clientFound = true;
                    } else if (choice.equalsIgnoreCase("изменить")) {
                        // Повторный запрос ID клиента
                    }
                }
            }

            operations[i].setCustomerId(customerId);

            if (type.equalsIgnoreCase("cashback")) {
                System.out.print("Сумма кэшбэка: ");
                double cashbackAmount = Double.parseDouble(scanner.nextLine());
                operations[i] = new CashbackOperation(id, type, amount, date, customerId, cashbackAmount);
            } else if (type.equalsIgnoreCase("loan")) {
                System.out.print("Проценты по кредиту: ");
                double interestRate = Double.parseDouble(scanner.nextLine());
                operations[i] = new LoanOperation(id, type, amount, date, customerId, interestRate);
            }

            saveOperationToStatement(id, customerId);
        }

        statement = new int[customers.length][operations.length];

        for (int i = 0; i < customers.length; i++) {
            for (int j = 0; j < operations.length; j++) {
                statement[i][j] = 0;
            }
        }

        for (int i = 0; i < operations.length; i++) {
            int customerId = operations[i].getCustomerId();
            int operationId = operations[i].getId();

            for (int j = 0; j < customers.length; j++) {
                if (customers[j] != null && customers[j].getId() == customerId) {
                    for (int k = 0; k < operations.length; k++) {
                        if (statement[j][k] == 0) {
                            statement[j][k] = operationId;
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
