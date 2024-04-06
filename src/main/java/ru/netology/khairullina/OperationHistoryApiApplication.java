package ru.netology.khairullina;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import ru.netology.khairullina.Service.CustomerService;
import ru.netology.khairullina.Service.OperationService;
import ru.netology.khairullina.Service.StatementService;

@SpringBootApplication
public class OperationHistoryApiApplication {
    public static final CustomerService cs = new CustomerService();
    public static final OperationService os = new OperationService();
    public static final StatementService ss = new StatementService();

   public static void main(String[] args) {

       SpringApplication.run(OperationHistoryApiApplication.class, args);
    }
}
