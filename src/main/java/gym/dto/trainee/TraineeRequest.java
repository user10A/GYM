package gym.dto.trainee;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;


@Getter
@Setter
public class TraineeRequest {
    private String firstName;
    private String lastName;
    private String address;
    private LocalDate dateOfBirth;

}
