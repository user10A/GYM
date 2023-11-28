package gym.dto;

import lombok.Data;

import java.time.LocalDate;
@Data
public class TrainerRequest {
    private String firstName;
    private String lastName;
    private String trainingType;

}
