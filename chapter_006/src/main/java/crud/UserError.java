package crud;

public class UserError extends RuntimeException {
    public UserError(String msg) {
        super("Error: " + msg);
    }
}