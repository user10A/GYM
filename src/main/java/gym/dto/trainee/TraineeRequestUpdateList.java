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
    private String email;
    private String address;
    private LocalDate dateOfBirth;

    public TraineeRequestUpdateList(User user, Trainee trainee) {
        this.firstName = user.getFirstName();
        this.lastName = user.getLastName();
        this.email = user.getUsername();
        this.address = trainee.getAddress();
        this.dateOfBirth = trainee.getDateOfBirth();
    }

    public TraineeRequestUpdateList(String firstName, String lastName, String email, String address, LocalDate dateOfBirth) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.address = address;
        this.dateOfBirth = dateOfBirth;
    }
}
