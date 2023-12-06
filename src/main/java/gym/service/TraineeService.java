package gym.service;

import gym.dto.trainee.*;
import gym.dto.trainer.TrainerResponse;
import gym.dto.user.*;

import java.util.List;

public interface TraineeService {

    List<TraineeResponse> getAllTrainee();
    AuthenticationResponse signUp(TraineeRequest2 customer);

    TraineeResponse update(UpdateRequest trainee);
    TraineeResponse getByEmail(String email);

    SimpleResponse delete(String email);
    Login updateIsActivityOrDeActiveByUserName(String email, boolean isActive);
    SimpleResponse updatePasswordTrainee(LoginChange change);
    AuthenticationResponse signIn(UserCheckRequest request);
    Update2Response updateTrainersList(UpdateRequest2 updateRequest);
}
