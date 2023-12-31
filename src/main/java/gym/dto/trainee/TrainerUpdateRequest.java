package gym.dto.trainee;

import gym.model.TrainingType;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class TrainerUpdateRequest {

    private String firstName;
    private String lastName;
    private String email;
    private Boolean isActive;
    private String trainingType;
}
