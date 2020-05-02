package error;

public class NoFlightsFromThisLocationException extends RuntimeException{
    public NoFlightsFromThisLocationException(String message) {
        super(message);
    }
}
