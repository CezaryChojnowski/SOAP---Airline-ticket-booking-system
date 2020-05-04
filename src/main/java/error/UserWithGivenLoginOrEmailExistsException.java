package error;

public class UserWithGivenLoginOrEmailExistsException extends RuntimeException{
    public UserWithGivenLoginOrEmailExistsException(String message) {
        super(message);
    }
}
