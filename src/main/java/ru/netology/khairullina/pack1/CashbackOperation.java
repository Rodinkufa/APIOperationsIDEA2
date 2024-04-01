package ru.netology.khairullina.pack1;

import java.time.LocalDate;

public class CashbackOperation extends Operation implements ConsolePrintable {
    private double cashbackAmount;

    public CashbackOperation(int id, String type, double amount, LocalDate date, int customerId, double cashbackAmount) {
        super(id, type, amount, date, customerId);
        this.cashbackAmount = cashbackAmount;
    }

    @Override
    public void print() {
        super.print(); // Выводим основную информацию об операции
        System.out.println("Операция с возвратом наличных:");
        System.out.println("Сумма возврата: " + cashbackAmount);
    }
}
