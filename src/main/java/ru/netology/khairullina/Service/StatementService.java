package ru.netology.khairullina.Service;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import ru.netology.khairullina.Model.Operation;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@Scope("singleton")
public class StatementService {

    private final Map<Integer, List<Operation>> statements  = new HashMap<>();

    public void PutStatement(int customerId, List<Operation> operationsList) {
        statements .put(customerId, operationsList);
    }

    public void PrintAll () {
        System.out.println(statements);
    }

    public void PrintCustomerOperations(int customerId) {
        var entry = statements.get(customerId);
        System.out.println(entry);
    }
}
