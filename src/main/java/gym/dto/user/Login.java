package gym.dto.user;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Login {
    private String userName;
    private boolean isActive;

    public Login(String userName, boolean isActive) {
        this.userName = userName;
        this.isActive = isActive;
    }
}
