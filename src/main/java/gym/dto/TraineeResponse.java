package gym.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
public class TraineeResponse {
    private Long Id;
    private String firstName;
    private String lastName;
    private String userName;
    private String password;
    private String address;
    private LocalDate dateOfBirth;
    boolean isActive;

    public TraineeResponse(Long id,String userName,String address, LocalDate dateOfBirth) {
        Id = id;
        this.userName = userName;
        this.address = address;
        this.dateOfBirth = dateOfBirth;
    }

    public TraineeResponse(Long id, String firstName, String lastName, String userName, String password, String address, LocalDate dateOfBirth, boolean isActive) {
        Id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.userName = userName;
        this.password = password;
        this.address = address;
        this.dateOfBirth = dateOfBirth;
        this.isActive = isActive;
    }

    public TraineeResponse(String userName, String password) {
        this.userName = userName;
        this.password = password;
    }

    @Override
    public String toString() {
        return "TraineeResponse{" +
                "userName='" + userName + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
