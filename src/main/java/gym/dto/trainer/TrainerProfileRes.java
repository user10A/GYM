package gym.dto.trainer;

import gym.model.Trainee;
import gym.model.Trainer;
import gym.model.TrainingType;
import gym.model.User;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class TrainerProfileRes {

    private String userName;
    private String firstName;
    private String lastName;
    private Boolean isActive;
    private String trainingType;
    private List<Trainee> trainees;


    public TrainerProfileRes(User user, Trainer trainer) {
        this.firstName=user.getFirstName();
        this.lastName=user.getLastName();
        this.isActive=user.isActive();
        this.trainingType= String.valueOf(trainer.getTrainingType());
        this.trainees=trainer.getTrainerTrainees();
    }


    public TrainerProfileRes(String userName, String firstName, String lastName, boolean active, List<Trainee> trainees, String trainingType) {
        this.userName=userName;
        this.firstName=firstName;
        this.lastName=lastName;
        this.isActive=active;
        this.trainees=trainees;
        this.trainingType=trainingType;
    }

    public TrainerProfileRes(String firstName, String lastName, boolean active,String trainingType, List<Trainee> trainees) {
        this.firstName=firstName;
        this.lastName=lastName;
        this.isActive=active;
        this.trainingType=trainingType;
        this.trainees=trainees;
    }
}
