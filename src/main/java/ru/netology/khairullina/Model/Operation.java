package ru.netology.khairullina.Model;
import java.io.Serializable;
import java.time.LocalDate;

public class Operation implements IConsolePrintable, Serializable {
    private int id;
    private String type;
    private double amount;
    private LocalDate date;
    private int customerId; // Добавлено поле для ID клиента

    // Конструктор без аргументов
    public Operation() {}

    // Конструктор со всеми полями, включая ID клиента
    public Operation(int id, String type, double amount, LocalDate date, int customerId) {
        this.id = id;
        this.type = type;
        this.amount = amount;
        this.date = date;
        this.customerId = customerId; // Инициализация поля ID клиента
    }

    // Геттеры и сеттеры
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    public void print() {
        System.out.println("Operation ID: " + id + ", Customer ID: " + customerId + ", Type: " + type + ", Amount: " + amount + ", Date: " + date);
    }
}
