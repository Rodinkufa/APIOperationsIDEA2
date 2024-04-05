package ru.netology.khairullina.Model;

public class OperationException extends Exception {

    public static class CustomerOperationOutOfBoundException extends OperationException {
        public static final String MESSAGE = "Exception while trying to save operation %s for customer %s";
        private int customerId;
        private int operationId;

        public CustomerOperationOutOfBoundException (int customerId, int operationId) {
            super();
            this.customerId = customerId;
            this.operationId = operationId;
        }

        @Override
        public String getMessage () {
            return MESSAGE.formatted(operationId, customerId);
        }
    }
    public static class OperationRuntimeException extends RuntimeException{ }
}
