package in.dev.gmsk.response;

import lombok.Data;
import org.springframework.http.HttpStatus;

@Data
public class WarningMessage {

    private String message;
    private String values;

    public WarningMessage(String message, String values) {
        this.message = message;
        this.values = values;
    }
}
