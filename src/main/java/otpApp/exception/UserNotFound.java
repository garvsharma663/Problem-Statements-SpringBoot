package otpApp.exception;

public class UserNotFound extends RuntimeException {
    public UserNotFound() {
        System.out.println("User not found");;
    }
}
