package gym.service;

import gym.dto.trainee.TrainerUpdateRequest;
import gym.dto.trainer.TrainerProfileRes;
import gym.dto.trainer.TrainerRequest;
import gym.dto.trainer.TrainerResponse;
import gym.dto.user.*;
import gym.model.TrainingType;

import java.util.List;

public interface TrainerService {
    List<TrainerResponse> getAllTrainers();
    AuthenticationResponse saveCustomer(TrainerRequest trainerRequest);

    TrainerProfileRes update(TrainerUpdateRequest trainer);
    TrainerResponse getByUserName(String UserName);

    SimpleResponse delete(String userName);

    Login updateIsActivityOrDeActiveByUserName(String userName, boolean isActive);
    SimpleResponse updatePasswordTrainer(LoginChange change);
    SimpleResponse loginTrainee(String userName, String password);
    List<TrainingType> getAllTrainingType();


}
