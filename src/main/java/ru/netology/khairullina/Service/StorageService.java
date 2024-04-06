package ru.netology.khairullina.Service;

import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;

@Service
public class StorageService<T> {

    private final List<T> customers = new ArrayList<>();
    public void Add (T item) {
        customers.add(item);
    }

    public void Delete (T item) {
        customers.remove(item);
    }

    public List<T> GetList() {
        return customers;
    }
}
