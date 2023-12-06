package gym.dto.user;


import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class UserCheckRequest {

    private String email;
    private String password;

}
