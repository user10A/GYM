package gym.service;

import gym.dto.*;

import java.util.List;

public interface TrainerService {
    List<TrainerResponse> getAllTrainers();
    UserDto saveCustomer(TrainerRequest trainerRequest);

    String update(long id, TrainerRequest trainerRequest);

    TrainerResponse getByUserName(String UserName);

    SimpleResponse delete(long id);

    SimpleResponse updateIsActivityOrDeActiveByUserName(String userName, boolean isActive);
    SimpleResponse updatePasswordTrainer(String userName, String password);
}
