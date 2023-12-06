package gym.dto.trainer;


import gym.model.Trainer;
import gym.model.User;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@NoArgsConstructor
public class TrainerProfileRes2 {
    private String email;
    private String firstName;
    private String lastName;
    private Boolean isActive;
    private String trainingType;

    public TrainerProfileRes2(User user, Trainer trainer) {
        this.email = user.getUsername();
        this.firstName = user.getFirstName();
        this.lastName = user.getLastName();
        this.isActive = user.isActive();
        this.trainingType = trainer.getTrainingType().getName();
    }



    public TrainerProfileRes2(String email, String firstName, String lastName, String trainingType, boolean active) {
        this.email=email;
        this.firstName=firstName;
        this.lastName=lastName;
       this.trainingType=trainingType;
       this.isActive=active;

    }
}

