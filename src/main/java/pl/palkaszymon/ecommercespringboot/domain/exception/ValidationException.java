package pl.palkaszymon.ecommercespringboot.domain.exception;

public class ValidationException extends RuntimeException {
    public ValidationException(String message) {
        super(message);
    }
}
