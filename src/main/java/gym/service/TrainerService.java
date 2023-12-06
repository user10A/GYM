package gym.service;

import gym.dto.trainee.TrainerUpdateRequest;
import gym.dto.trainer.TrainerProfileRes;
import gym.dto.trainer.TrainerRequest;
import gym.dto.trainer.TrainerRequest2;
import gym.dto.trainer.TrainerResponse;
import gym.dto.user.*;
import gym.model.TrainingType;

import java.util.List;

public interface TrainerService {
    List<TrainerResponse> getAllTrainers();
    AuthenticationResponse signUp(TrainerRequest2 trainerRequest);

    TrainerProfileRes update(TrainerUpdateRequest trainer);
    TrainerResponse getByEmail(String email);

    SimpleResponse delete(String email);

    Login updateIsActivityOrDeActiveByUserName(String email, boolean isActive);
    SimpleResponse updatePasswordTrainer(LoginChange change);
    AuthenticationResponse signIn(UserCheckRequest request);
    List<TrainingType> getAllTrainingType();


}
