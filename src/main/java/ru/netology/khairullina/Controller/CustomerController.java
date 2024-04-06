package ru.netology.khairullina.Controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.netology.khairullina.Model.Customer;
import ru.netology.khairullina.Service.CustomerService;

import java.util.List;

@RestController
@RequestMapping(path = "api/customers")
public class CustomerController {

    private final CustomerService customerService;
    public CustomerController(CustomerService customerService) {

        this.customerService = customerService;
    }

    @GetMapping
    public List<Customer> getCustomers(){
        return customerService.getCustomers();
    }
    @GetMapping("{id}")
    public Customer getCustomer(@PathVariable("id")int id){

        return customerService.getCustomer(id);
    }
}
