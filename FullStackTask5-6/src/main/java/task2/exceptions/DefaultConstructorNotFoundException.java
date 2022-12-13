package task2.exceptions;

public class DefaultConstructorNotFoundException extends RuntimeException{
    public DefaultConstructorNotFoundException(String message) {
        super(message);
    }
}
