package ru.netology.khairullina.Model;

import java.io.Serializable;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

public class OperationData implements Serializable {
    private int customerId;
    private int operationId;
    private int[][] statement;

    /*public OperationData(int customerId, int operationId, int[][] statement) {
        this.customerId = customerId;
        this.operationId = operationId;
        this.statement = statement;
    }*/

    public int getOperationId() {
        return operationId;
    }

    public int getCustomerId() {
        return customerId;
    }

    public int[][] getStatement() {
        return statement;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    public void setStatement(int[][] statement) {
        this.statement = statement;
    }

    public void setOperationId(int operationId) {
        this.operationId = operationId;
    }
}
