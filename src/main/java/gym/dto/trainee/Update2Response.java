package gym.dto.trainee;


import gym.dto.trainer.TrainerInfo;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.stream.Collectors;

@Getter
@Setter
public class Update2Response {


    private List<TrainerInfo> trainersInfo;


    public Update2Response(String firstName, String lastName, String email, String trainingType) {
        this.trainersInfo = List.of(new TrainerInfo(firstName, lastName, email, trainingType));
    }

    public Update2Response(List<Update2Response> updateResponses) {
        this.trainersInfo = updateResponses.stream()
                .flatMap(updateResponse -> updateResponse.getTrainersInfo().stream())
                .collect(Collectors.toList());
    }
}

