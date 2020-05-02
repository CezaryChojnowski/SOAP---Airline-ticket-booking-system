package error;

public class InvalidLoginDataException extends RuntimeException{
    public InvalidLoginDataException(String message) {
        super(message);
    }
}
