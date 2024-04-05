package ru.netology.khairullina.Model;

import java.time.LocalDate;

public class LoanOperation extends Operation implements IConsolePrintable {
    private final double interestRate;

    public LoanOperation(int id, String type, double amount, LocalDate date, int customerId, double interestRate) {
        super(id, type, amount, date, customerId);
        this.interestRate = interestRate;
    }

    @Override
    public void print() {
        super.print(); // Выводим основную информацию об операции
        System.out.println("Операция по выдаче кредита:");
        System.out.println("Проценты по кредиту: " + interestRate);
    }
}
