package gym.dto.trainee;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.Date;


@Getter
@Setter
public class UpdateRequest {
    private String address;
    private LocalDate dateOfBirth;
    private String firstName;
    private String lastName;
    private String userName;
    private Boolean isActive;
}
