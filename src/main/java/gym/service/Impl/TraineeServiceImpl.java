package gym.service.Impl;

import gym.dto.*;
import gym.model.Trainee;
import gym.model.User;
import gym.repo.TraineeRepo;
import gym.service.TraineeService;
import gym.service.UserService;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Service
@Transactional
public class TraineeServiceImpl implements TraineeService {
    @Autowired
    private TraineeRepo traineeRepo;
    @Autowired
    private UserService userRepo;
    @Override
    public List<TraineeResponse> getAllCustomers()
    {
        return traineeRepo.findAllTrainees();
    }

    @Override
    public UserDto saveCustomer(TraineeRequest traineeRequest) {
        User user = new User();
        user.setFirstName(traineeRequest.getFirstName());
        user.setLastName(traineeRequest.getLastName());
        user.setPassword(userRepo.generatePassword());
        user.setUserName(userRepo.generateUsername(traineeRequest.getFirstName(),traineeRequest.getLastName()));
        userRepo.save(user);

        Trainee trainee1 = Trainee.builder()
                .dateOfBirth(traineeRequest.getDateOfBirth())
                .address(traineeRequest.getAddress())
                .user(user)
                .build();
        traineeRepo.save(trainee1);
        return new UserDto(
              trainee1.getUser().getUserName(),
                trainee1.getUser().getPassword()
        );
    }

    @Override
    public String update(long id, TraineeRequest trainee) {
        Trainee trainee1 = traineeRepo.findById(id).orElseThrow(
                ()-> new NoSuchElementException
                        (String.format("Trainee with such and id %d doesnt exist",id)));
        trainee1.setDateOfBirth(trainee.getDateOfBirth());
        trainee1.getUser().setFirstName(trainee.getFirstName());
        trainee1.getUser().setLastName(trainee.getLastName());
        trainee1.setAddress(trainee.getAddress());
        traineeRepo.save(trainee1);
        return "customer with id" + trainee1.getId() + " successfully updated";};

    @Override
    public TraineeResponse getByUserName(String userName) {
        Trainee trainee =traineeRepo.findTraineeByUsername(userName);
        return new TraineeResponse (
                trainee.getId(),
                trainee.getUser().getFirstName(),
                trainee.getUser().getLastName(),
                trainee.getUser().getUserName(),
                trainee.getUser().getPassword(),
                trainee.getAddress(),
                trainee.getDateOfBirth(),
                trainee.getUser().isActive()
        );
    }

    @Override
    public SimpleResponse delete(long id) {
        traineeRepo.deleteById(id);
        return new SimpleResponse("Customer with id " + id + " successfully deleted", HttpStatus.OK);
    }

    @Override
    public SimpleResponse updateIsActivityOrDeActiveByUserName(String userName, boolean isActive) {
        traineeRepo.updateIsActivityByUserName(userName,isActive);
        return new SimpleResponse("Trainee IsActivity successfully updated", HttpStatus.OK);
    }

    @Override
    public SimpleResponse updatePasswordTrainee(String userName, String password) {
        traineeRepo.updatePasswordTrainee(userName,password);
        return new SimpleResponse("Trainee password successfully updated", HttpStatus.OK);
    }
}
