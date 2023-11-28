package gym.dto;

import gym.model.Specialization;
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
    private String specializationName;
    private String userName;
    private String password;
    boolean isActive;

    public TrainerResponse(Long id, String specializationName, String userName, Specialization specialization, String name, String password, boolean active) {
        this.id = id;
        this.specializationName = specializationName;
        this.userName = userName;
    }

    public TrainerResponse(Long id, String firstName, String lastName, String specializationName, String userName, String password, boolean isActive) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.specializationName = specializationName;
        this.userName = userName;
        this.password = password;
        this.isActive = isActive;
    }

    public TrainerResponse(String userName, String password, Specialization specialization, String name, String s, boolean active) {
        this.userName = userName;
        this.password=password;
    }

    @Override
    public String toString() {
        return "TrainerResponse{" +
                "userName='" + userName + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
