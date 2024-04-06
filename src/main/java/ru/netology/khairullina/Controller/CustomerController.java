package ru.netology.khairullina.Controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
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

    @PostMapping
    public void addCustomer(@RequestBody Customer customer){
        customerService.addCustomer(customer);
    }

    @PostMapping(path = "add", params={"id", "name", "email"})
    public ResponseEntity<Void> addCustomer(@RequestParam("id")int id,
                                            @RequestParam("name") String name,
                                            @RequestParam("email") String email) {
        customerService.addCustomer(id, name, email);

        return new ResponseEntity<Void>(HttpStatus.OK);
    }

    // обновление
    @PutMapping
    public ResponseEntity<Void> updateArticle(@RequestBody Customer customer) {
        boolean res = customerService.updateCustomer( customer);
        if (res)
            return new ResponseEntity<Void>(HttpStatus.OK);
        else
            return new ResponseEntity<Void>(HttpStatus.NOT_FOUND);
    }

    // удалить по ИД
    @DeleteMapping("{id}")
    public ResponseEntity<Void> deleteArticle(@PathVariable("id") Integer customerId) {
        boolean res = customerService.deleteCustomer(customerId);
        if (res)
            return new ResponseEntity<Void>(HttpStatus.OK);
        else
            return new ResponseEntity<Void>(HttpStatus.NOT_FOUND);
    }
}
