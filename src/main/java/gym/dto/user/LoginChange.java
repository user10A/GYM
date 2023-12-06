package gym.dto.user;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LoginChange {

    private String email;
    private String oldPassword;
    private String newPassword;

}
