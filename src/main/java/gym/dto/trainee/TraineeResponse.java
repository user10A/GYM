package gym.dto.trainee;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
public class TraineeResponse {
    private Long Id;
    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private String address;
    private LocalDate dateOfBirth;
    private boolean isActive;

    public TraineeResponse(Long id,String email,String address, LocalDate dateOfBirth) {
        Id = id;
        this.email = email;
        this.address = address;
        this.dateOfBirth = dateOfBirth;
    }

    public TraineeResponse(Long id, String firstName, String lastName, String email, String password, String address, LocalDate dateOfBirth, boolean isActive) {
        Id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
        this.address = address;
        this.dateOfBirth = dateOfBirth;
        this.isActive = isActive;
    }
}
