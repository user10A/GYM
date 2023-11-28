package gym.service.Impl;
import gym.dto.*;
import gym.model.Specialization;
import gym.model.Trainee;
import gym.model.Trainer;
import gym.model.User;
import gym.repo.TrainerRepo;
import gym.service.TrainerService;
import gym.service.UserService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.NoSuchElementException;

@Service
@Transactional
public class TrainerServiceImpl implements TrainerService {
    @Autowired
    private TrainerRepo trainerRepo;
    @Autowired
    private UserService userRepo;

    @Override
    public List<TrainerResponse> getAllTrainers() {
        return trainerRepo.findAllTrainers();
    }

    @Override
    public UserDto saveCustomer(TrainerRequest trainerRequest) {
        User user = new User();
        user.setFirstName(trainerRequest.getFirstName());
        user.setLastName(trainerRequest.getLastName());
        user.setPassword(userRepo.generatePassword());
        user.setUserName(userRepo.generateUsername(trainerRequest.getFirstName(),trainerRequest.getLastName()));
        userRepo.save(user);

        Trainer trainer1 = Trainer.builder()
                .user(user)
                .build();
        trainerRepo.save(trainer1);
        return new UserDto(
                trainer1.getUser().getUserName(),
                trainer1.getUser().getPassword()
        );
    }

    @Override
    public String update(long id, TrainerRequest trainerRequest) {
        Trainer trainer1 = trainerRepo.findById(id).orElseThrow(
                ()-> new NoSuchElementException
                        (String.format("Trainer with such and id %d doesnt exist",id)));
        trainer1.getUser().setFirstName(trainerRequest.getFirstName());
        trainer1.getUser().setLastName(trainerRequest.getLastName());
        trainerRepo.save(trainer1);
        return "customer with id" + trainer1.getId() + " successfully updated";};

        @Override
    public TrainerResponse getByUserName(String userName) {
       Trainer trainer = trainerRepo.findTrainerByUsername(userName);
       return new TrainerResponse(
               trainer.getId(),
               trainer.getUser().getFirstName(),
               trainer.getUser().getLastName(),
               trainer.getSpecialization(),
               trainer.getUser().getUserName(),
               trainer.getUser().getPassword(),
               trainer.getUser().isActive()
       );
    }


    @Override
    public SimpleResponse delete(long id) {
        trainerRepo.deleteById(id);
        return new SimpleResponse("Trainer with id " + id + " successfully deleted", HttpStatus.OK);
    }

    @Override
    public SimpleResponse updateIsActivityOrDeActiveByUserName(String userName, boolean isActive) {
            trainerRepo.updateIsActivityByUserName(userName,isActive);
        return new SimpleResponse("Trainer IsActivity successfully updated", HttpStatus.OK);    }

    @Override
    public SimpleResponse updatePasswordTrainer(String userName, String password) {
            trainerRepo.updatePasswordTrainer(userName,password);
        return new SimpleResponse("Trainer password successfully updated", HttpStatus.OK);    }
}
