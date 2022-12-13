package task2.exceptions;

public class SetterNotFoundException extends RuntimeException{
    public SetterNotFoundException(String message) {
        super(message);
    }
}
