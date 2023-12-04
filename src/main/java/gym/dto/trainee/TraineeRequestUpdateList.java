package gym.dto.trainee;

import gym.model.Trainee;
import gym.model.User;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class TraineeRequestUpdateList {
    private String firstName;
    private String lastName;
    private String userName;
    private String address;
    private LocalDate dateOfBirth;

    public TraineeRequestUpdateList(User user, Trainee trainee) {
        this.firstName = user.getFirstName();
        this.lastName = user.getLastName();
        this.userName = user.getUserName();
        this.address = trainee.getAddress();
        this.dateOfBirth = trainee.getDateOfBirth();
    }

    public TraineeRequestUpdateList(String firstName, String lastName, String userName, String address, LocalDate dateOfBirth) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.userName = userName;
        this.address = address;
        this.dateOfBirth = dateOfBirth;
    }
}
