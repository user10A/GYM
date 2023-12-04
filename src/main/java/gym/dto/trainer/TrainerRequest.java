package gym.dto.trainer;

import gym.model.TrainingType;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
@Getter
@Setter
public class TrainerRequest {
    private String firstName;
    private String lastName;
    private String trainingType;

}
