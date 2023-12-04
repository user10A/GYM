package gym.dto.trainee;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Getter
@Setter
public class UpdateTrainee {

    private List<TraineeRequestUpdateList> traineeRequestUpdateLists;

    public UpdateTrainee(String firstName, String lastName, String userName, String address, LocalDate dateOfBirth) {
        this.traineeRequestUpdateLists = List.of(new TraineeRequestUpdateList(firstName, lastName, userName, address, dateOfBirth));
    }
    public UpdateTrainee(List<UpdateTrainee> updateResponses) {
        this.traineeRequestUpdateLists = updateResponses.stream()
                .flatMap(updateResponse -> updateResponse.getTraineeRequestUpdateLists().stream())
                .collect(Collectors.toList());
    }
}
