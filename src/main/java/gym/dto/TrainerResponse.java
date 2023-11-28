package gym.dto;

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
    private String userName;
    private String password;
    boolean isActive;


    public TrainerResponse(Long id, String trainingType, String userName, TrainingType type, String name, String password, boolean active) {
        this.id = id;
        this.trainingType = trainingType;
        this.userName = userName;
    }

    public TrainerResponse(Long id, String firstName, String lastName, String trainingType, String userName, String password, boolean isActive) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.trainingType = trainingType;
        this.userName = userName;
        this.password = password;
        this.isActive = isActive;
    }

    public TrainerResponse(String userName, String password) {
        this.userName = userName;
        this.password = password;
    }

    @Override
    public String toString() {
        return "TrainerResponse{" +
                "userName='" + userName + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
