package gym.dto.trainer;

import gym.model.TrainingType;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class TrainerResponse {
    private Long id;
    private String firstName;
    private String lastName;
    private String trainingType;
    private String email;
    private String password;
    boolean isActive;


    public TrainerResponse(Long id, String trainingType, String email, TrainingType type, String name, String password, boolean active) {
        this.id = id;
        this.trainingType = trainingType;
        this.email = email;
    }

    public TrainerResponse(Long id, String firstName, String lastName, String trainingType, String email, String password, boolean isActive) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.trainingType = trainingType;
        this.email = email;
        this.password = password;
        this.isActive = isActive;
    }

    public TrainerResponse(String email, String password) {
        this.email = email;
        this.password = password;
    }

    @Override
    public String toString() {
        return "TrainerResponse{" +
                "userName='" + email + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
