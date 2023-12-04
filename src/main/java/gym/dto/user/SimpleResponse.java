package gym.dto.user;

import lombok.*;
import org.springframework.http.HttpStatus;

@Getter
@Setter
@AllArgsConstructor
@Builder
public class SimpleResponse {
    private HttpStatus status;
    private String message;

    public SimpleResponse() {
    }

    public SimpleResponse(String message, HttpStatus status) {
        this.status = status;
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
