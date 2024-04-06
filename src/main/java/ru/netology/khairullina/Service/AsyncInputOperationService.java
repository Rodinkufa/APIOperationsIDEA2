package ru.netology.khairullina.Service;

import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.netology.khairullina.Config.OperationConfig;
import ru.netology.khairullina.Model.Operation;

import java.util.LinkedList;
import java.util.Queue;

@Service
public class AsyncInputOperationService {

    private final Queue<Operation> queue = new LinkedList<>();

    @Autowired
    private OperationService operationService;

    @Autowired
    private OperationConfig operationConfig;

    public boolean addOperation(Operation operation) {
        return queue.offer(operation);
    }

    @PostConstruct
    public void init(){
        this.startAsyncOperationProcessing();
    }

    private void processQueue() {
        while (true) {
            Operation operation = queue.poll();
            if (operation == null) {
                try {
                    System.out.println("Waiting for next operation in queue");
                    Thread.sleep(operationConfig.getSleepmillyseconds());
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            } else {
                System.out.println("Processing operation:" + operation);
                operationService.AddOperation(operation);
            }
        }
    }
     public void startAsyncOperationProcessing() {
        Thread t = new Thread() {
            @Override
            public void run() {
                processQueue();
            }
        };
        t.start();
    }

}
