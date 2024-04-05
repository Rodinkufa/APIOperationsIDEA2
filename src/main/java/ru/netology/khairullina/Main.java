package ru.netology.khairullina;

import ru.netology.khairullina.Model.*;
import ru.netology.khairullina.Model.OperationException.CustomerOperationOutOfBoundException;
import ru.netology.khairullina.Service.CustomerService;
import ru.netology.khairullina.Service.OperationService;

import java.io.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {
     public static final CustomerService cs = new CustomerService();
    public static final OperationService os = new OperationService();








//    public static void saveOperationToStatement(int operationId, int customerId) {
//        for (int j = 0; j < customers.length; j++) {
//            if (customers[j] != null && customers[j].getId() == customerId) {
//                for (int k = 0; k < operations.length; k++) {
//                    if (data.getStatement()[j][k] == 0) {
//                        data.getStatement()[j][k] = operationId;
//                        return;
//                    }
//                }
//                System.out.println("Ошибка: не удалось сохранить операцию. Нет места в выписке.");
//                return;
//            }
//        }
//        System.out.println("Ошибка: клиент с ID " + customerId + " не найден.");
//    }
//
   public static void main(String[] args) {

       for (int j = 0; j < 3; j++) {

           Customer c = cs.CreateCustomer();
           cs.AddCustomer(c);

       }

       for (int j = 0; j < 2; j++) {

           Operation o = os.CreateOperation();
           os.AddOperation(o);

       }

       Scanner scanner = new Scanner(System.in);
       System.out.print("Введите ID клиента, чтобы получить его операции: ");
       int clientId = Integer.parseInt(scanner.nextLine());
       System.out.println("Операции клиента с ID " + clientId + ":");
       os.printOperations(clientId);
//
//        scanner.close();

//        AddTransaction();
//
//        for (int i = 0; i < customers.length; i++) {
//            for (int j = 0; j < operations.length; j++) {
//                data.getStatement()[i][j] = 0;
//            }
//        }
//
//        for (int i = 0; i < operations.length; i++) {
//            data.setCustomerId(operations[i].getCustomerId());
//            data.setOperationId(operations[i].getId());
//
//            for (int j = 0; j < customers.length; j++) {
//                if (customers[j] != null && customers[j].getId() == data.getCustomerId()) {
//                    for (int k = 0; k < operations.length; k++) {
//                        if (data.getStatement()[j][k] == 0) {
//                            //data.setStatement([data.getCustomerId()][data.getOperationId()]);
//                            break;
//                        }
//                    }
//                    break;
//                }
//            }
//        }
//
//        for (Operation operation : operations) {
//            operation.print();
//        }
//
////        System.out.println("Массив statement:");
////        for (int i = 0; i < customers.length; i++) {
////            System.out.print("Клиент " + (i + 1) + ": ");
////            for (int j = 0; j < operations.length; j++) {
////                if (statement[i][j] != 0) {
////                    System.out.print(statement[i][j] + " ");
////                }
////            }
////            System.out.println();
////        }

//        System.out.print("Введите ID клиента, чтобы получить его операции: ");
//        int clientId = Integer.parseInt(scanner.nextLine());
//        Operation[] clientOperations = getOperations(clientId);
//        System.out.println("Операции клиента с ID " + clientId + ":");
//        printOperations(clientOperations);
//
//        scanner.close();
    }
}
