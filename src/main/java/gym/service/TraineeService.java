package gym.service;

import gym.dto.*;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface TraineeService {

    List<TraineeResponse> getAllCustomers();
    UserDto saveCustomer(TraineeRequest customer);

    String update(long id, TraineeRequest customer);

    TraineeResponse getByUserName(String UserName);

    SimpleResponse delete(long id);
    SimpleResponse updateIsActivityOrDeActiveByUserName(String userName, boolean isActive);
    SimpleResponse updatePasswordTrainee(String userName, String password);
}
