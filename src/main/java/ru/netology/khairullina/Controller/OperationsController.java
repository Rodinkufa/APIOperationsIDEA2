package ru.netology.khairullina.Controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.netology.khairullina.Model.Customer;
import ru.netology.khairullina.Model.Operation;
import ru.netology.khairullina.Service.AsyncInputOperationService;
import ru.netology.khairullina.Service.CustomerService;
import ru.netology.khairullina.Service.OperationService;

import java.util.List;

@RestController
@RequestMapping(path = "api/operations")
public class OperationsController {
    private final CustomerService cs;
    private final OperationService os;

    public OperationsController(AsyncInputOperationService  service, CustomerService cs, OperationService os) {
        this.cs = cs;
        this.os = os;
    }

    @GetMapping("{id}")
    public List<Operation> getOperations(@PathVariable("id")int customerId) {
        return os.getOperations(customerId);
    }

    @PostMapping
    public ResponseEntity<Void> addOperation(@RequestBody Operation operation){
        Customer findCustomer = cs.getCustomer(operation.getCustomerId());
        if (findCustomer == null)
            return new ResponseEntity<Void>(HttpStatus.NOT_FOUND);

        os.AddOperation(operation);
        return new ResponseEntity<Void>(HttpStatus.OK);
    }
}
