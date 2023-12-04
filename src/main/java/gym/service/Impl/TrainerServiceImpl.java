package gym.service.Impl;
import gym.dto.trainee.TrainerUpdateRequest;
import gym.dto.trainer.TrainerProfileRes;
import gym.dto.trainer.TrainerRequest;
import gym.dto.trainer.TrainerResponse;
import gym.dto.user.*;
import gym.model.Trainer;
import gym.model.TrainingType;
import gym.model.User;
import gym.repo.TrainerRepo;
import gym.repo.TrainingTypeRepo;
import gym.service.TrainerService;
import gym.service.UserService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Service
@Transactional
public class TrainerServiceImpl implements TrainerService {
    @Autowired
    private TrainerRepo trainerRepo;
    @Autowired
    private UserService userRepo;
    @Autowired
    private TrainingTypeRepo trainingTypeRepo;

    @Override
    public List<TrainerResponse> getAllTrainers() {
        return trainerRepo.findAllTrainers();
    }

    @Override
    public UserDto saveCustomer(TrainerRequest trainerRequest) {
        User user = new User();
        user.setFirstName(trainerRequest.getFirstName());
        user.setLastName(trainerRequest.getLastName());

        // Установка пароля и имени пользователя
        user.setPassword(userRepo.generatePassword());
        user.setUserName(userRepo.generateUsername(trainerRequest.getFirstName(), trainerRequest.getLastName()));

        // Сохранение пользователя
        userRepo.save(user);

        // Создание и сохранение объекта TrainingType
        TrainingType trainingType = new TrainingType();
        trainingType.setName(trainerRequest.getTrainingType());
        trainingTypeRepo.save(trainingType);

        // Создание и сохранение объекта Trainer
        Trainer trainer1 = Trainer.builder()
                .user(user)
                .trainingType(trainingType)
                .build();
        trainerRepo.save(trainer1);

        // Возврат данных пользователя
        return new UserDto(
                trainer1.getUser().getUserName(),
                trainer1.getUser().getPassword()
        );
    }

    @Override
    public TrainerProfileRes update(TrainerUpdateRequest trainer) {
        Trainer trainer1 = trainerRepo.findTrainerByUsername(trainer.getUserName());
        User user = trainer1.getUser();
        user.setUserName(trainer.getUserName());
        user.setFirstName(trainer.getFirstName());
        user.setLastName(trainer.getLastName());
        user.setActive(trainer.getIsActive());
        trainerRepo.save(trainer1);
        return new TrainerProfileRes(
                trainer1.getUser().getUserName(),
                trainer1.getUser().getFirstName(),
                trainer1.getUser().getLastName(),
                trainer1.getUser().isActive(),
                trainer1.getTrainerTrainees(),
                trainer1.getTrainingType().getName()
                );
    }

        @Override
    public TrainerResponse getByUserName(String userName) {
       Trainer trainer = trainerRepo.findTrainerByUsername(userName);
       return new TrainerResponse(
               trainer.getId(),
               trainer.getUser().getFirstName(),
               trainer.getUser().getLastName(),
               trainer.getTrainingType(),
               trainer.getUser().getUserName(),
               trainer.getUser().getPassword(),
               trainer.getUser().isActive()
       );
    }


    @Override
    public SimpleResponse delete(String userName) {
        Trainer trainer = trainerRepo.findTrainerByUsername(userName);
        if (trainer!=null) {
            trainerRepo.delete(trainer);
            return new SimpleResponse("Trainer successfully deleted", HttpStatus.OK);
        }else {
            return new SimpleResponse("Trainer by userName: "+userName+" not found",HttpStatus.NOT_FOUND);
        }
    }

    @Override
    public Login updateIsActivityOrDeActiveByUserName(String userName, boolean isActive) {
        Trainer trainer = trainerRepo.findTrainerByUsername(userName);
        trainerRepo.updateIsActivityByUserName(userName, isActive);
        trainerRepo.save(trainer);
        return new Login(trainer.getUser().getUserName(),trainer.getUser().isActive());
    }

    @Override
    public SimpleResponse updatePasswordTrainer(LoginChange change) {
            trainerRepo.updatePasswordTrainer(change.getUsername(),change.getNewPassword());
        return new SimpleResponse("Trainer password successfully updated", HttpStatus.OK);    }

    @Override
    public SimpleResponse loginTrainee(String userName, String password) {
       Trainer trainer= trainerRepo.trainerPasswordChange(userName,password);
        if (trainer != null) {
            User user =trainer.getUser();
            if (user.getPassword().equals(trainer.getUser().getPassword())||user.getUserName().equals(userName)) {
                return SimpleResponse.builder()
                        .status(HttpStatus.OK)
                        .message("Username and Password correct!")
                        .build();
            } else {
                return SimpleResponse.builder()
                        .status(HttpStatus.UNAUTHORIZED)
                        .message("Incorrect password")
                        .build();
            }
        } else {
            return SimpleResponse.builder()
                    .status(HttpStatus.UNAUTHORIZED)
                    .message("Incorrect username")
                    .build();
        }
    }

    @Override
    public List<TrainingType> getAllTrainingType() {
        return (List<TrainingType>) trainingTypeRepo.findTrainingTypes();
    }
}
