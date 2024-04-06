package ru.netology.khairullina.Service;

import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;

@Service
public class StorageService<T> {

    private final List<T> customers = new ArrayList<>();
    public void Add (T item) {
        customers.add(item);
    }

    public List<T> GetList() {
        return customers;
    }
}
