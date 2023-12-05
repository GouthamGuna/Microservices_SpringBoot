package in.dev.gmsk.response;

public class UserNotFound {

    private String message;

    public UserNotFound(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
