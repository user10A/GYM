package gym.service;

import gym.dto.trainee.*;
import gym.dto.trainer.TrainerResponse;
import gym.dto.user.Login;
import gym.dto.user.LoginChange;
import gym.dto.user.SimpleResponse;
import gym.dto.user.UserDto;

import java.util.List;

public interface TraineeService {

    List<TraineeResponse> getAllCustomers();
    UserDto saveCustomer(TraineeRequest customer);

    TraineeResponse update(UpdateRequest trainee);
    TraineeResponse getByUserName(String UserName);

    SimpleResponse delete(String userName);
    Login updateIsActivityOrDeActiveByUserName(String userName, boolean isActive);
    SimpleResponse updatePasswordTrainee(LoginChange change);
    SimpleResponse logonTrainee(String userName, String password);
    Update2Response updateTrainersList(UpdateRequest2 updateRequest);
}
