package gym.dto;

import lombok.Data;

import java.time.LocalDate;

@Data
public class TraineeRequest {
    private String firstName;
    private String lastName;
    private String address;
    private LocalDate dateOfBirth;

}
