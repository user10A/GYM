package gym.dto.user;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Login {
    private String email;
    private boolean isActive;

    public Login(String email, boolean isActive) {
        this.email = email;
        this.isActive = isActive;
    }
}
