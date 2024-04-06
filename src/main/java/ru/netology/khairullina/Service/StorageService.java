package ru.netology.khairullina.Service;

import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;

@Service
public class StorageService<T> {

    private final List<T> list = new ArrayList<>();
    public void Add (T item) {
        list.add(item);
    }

    public void Delete (T item) {
        list.remove(item);
    }

    public List<T> GetList() {
        return list;
    }
}
