package gym.service.Impl;
import gym.config.jwt.JwtService;
import gym.dto.trainee.*;
import gym.dto.user.*;
import gym.enums.Role;
import gym.model.Trainee;
import gym.model.Trainer;
import gym.model.User;
import gym.repo.TraineeRepo;
import gym.repo.TrainerRepo;
import gym.service.TraineeService;
import gym.service.UserService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
@RequiredArgsConstructor
public class TraineeServiceImpl implements TraineeService {
    private final TraineeRepo traineeRepo;
    private final TrainerRepo trainerRepo;
    private final UserService userService;
    private final JwtService jwtService;
    private final PasswordEncoder passwordEncoder;
    @Override
    public List<TraineeResponse> getAllCustomers()
    {
        return traineeRepo.findAllTrainees();
    }

    @Override
    public AuthenticationResponse saveCustomer(TraineeRequest2 traineeRequest) {
        User user = new User();
        user.setFirstName(traineeRequest.getFirstName());
        user.setLastName(traineeRequest.getLastName());
        user.setPassword(traineeRequest.getPassword());
        user.setRole(Role.TRAINEE);
        user.setUserName(traineeRequest.getUserName());
        userService.save(user);

        Trainee trainee1 = Trainee.builder()
                .dateOfBirth(traineeRequest.getDateOfBirth())
                .address(traineeRequest.getAddress())
                .user(user)
                .build();
        traineeRepo.save(trainee1);
        String token = jwtService.generateToken(user);
        return AuthenticationResponse.builder().
                token(token)
                .userName(user.getUsername())
                .password(user.getPassword())
                .role(user.getRole())
                .build();
//        User user = new User();
//        user.setFirstName(traineeRequest.getFirstName());
//        user.setLastName(traineeRequest.getLastName());
//        user.setPassword(userRepo.generatePassword());
//        user.setUserName(userRepo.generateUsername(traineeRequest.getFirstName(),traineeRequest.getLastName()));
//        userRepo.save(user);
//
//        Trainee trainee1 = Trainee.builder()
//                .dateOfBirth(traineeRequest.getDateOfBirth())
//                .address(traineeRequest.getAddress())
//                .user(user)
//                .build();
//        traineeRepo.save(trainee1);
//        String token = jwtService.generateToken(user);
//        return AuthenticationResponse.builder().
//                token(token)
//                .userName(user.getUsername())
//                .role(user.getRole())
//                .build();

    }

    @Override
    public TraineeResponse update(UpdateRequest trainee) {
        Trainee trainee1 = traineeRepo.findTraineeByUsername(trainee.getUserName());
        if (trainee1 == null) {
            // Обработайте ситуацию, когда trainee1 равен null. Например, вы можете выбросить исключение или вернуть null.
            throw new RuntimeException("Trainee not found with username: " + trainee.getUserName());
        }
        trainee1.setDateOfBirth(trainee.getDateOfBirth());
        trainee1.getUser().setFirstName(trainee.getFirstName());
        trainee1.getUser().setLastName(trainee.getLastName());
        trainee1.setAddress(trainee.getAddress());
        traineeRepo.save(trainee1);
        return new TraineeResponse(
                trainee1.getId(),
                trainee1.getUser().getFirstName(),
                trainee1.getUser().getLastName(),
                trainee1.getUser().getUsername(),
                trainee1.getUser().getPassword(),
                trainee1.getAddress(),
                trainee1.getDateOfBirth(),
                trainee1.getUser().isActive());
    }


    @Override
    public TraineeResponse getByUserName(String userName) {
        Trainee trainee =traineeRepo.findTraineeByUsername(userName);
        return new TraineeResponse (
                trainee.getId(),
                trainee.getUser().getFirstName(),
                trainee.getUser().getLastName(),
                trainee.getUser().getUsername(),
                trainee.getUser().getPassword(),
                trainee.getAddress(),
                trainee.getDateOfBirth(),
                trainee.getUser().isActive()
        );
    }
    @Override
    public SimpleResponse delete(String userName) {
        Trainee trainee = traineeRepo.findTraineeByUsername(userName);
        if (trainee!=null){
            return new SimpleResponse("Trainee by userName: " +userName+ " successfully deleted", HttpStatus.OK);
        }else {
            return new SimpleResponse("Trainee by userName"+ userName + "not found ",HttpStatus.NOT_FOUND);
        }
    }

    @Override
    public Login updateIsActivityOrDeActiveByUserName(String userName, boolean isActive) {
        Trainee trainee = traineeRepo.findTraineeByUsername(userName);
        traineeRepo.updateIsActivityByUserName(userName,isActive);
        traineeRepo.save(trainee);
        return new Login(trainee.getUser().getUsername(),trainee.getUser().isActive());
    }

    @Override
    public SimpleResponse updatePasswordTrainee(LoginChange change) {
        String oldPassword =change.getOldPassword();
        traineeRepo.updatePasswordTrainee(change.getUsername(), change.getNewPassword());
        return new SimpleResponse("Trainee password successfully updated", HttpStatus.OK);
    }

    @Override
    public SimpleResponse logonTrainee(String userName, String password) {
        traineeRepo.traineePasswordChange(userName,password);
        return new SimpleResponse("200 OK",HttpStatus.OK);

    }

    @Override
    public Update2Response updateTrainersList(UpdateRequest2 updateRequest) {
        Trainee trainee = traineeRepo.findTraineeByUserUsername(updateRequest.getTraineeUsername());

        List<String> trainersUsername = updateRequest.getUsernames();
        List<Trainer> trainers = trainerRepo.findByTrainerUserNameIn(trainersUsername);
        List<Trainer> exist = trainee.getTraineeTrainers();

        List<Trainer> newTrainers = trainers.stream()
                .filter(trainer -> !exist.contains(trainer))
                .toList();

        exist.addAll(newTrainers);
        trainee.setTraineeTrainers(exist);
        traineeRepo.save(trainee);

        List<Update2Response> updateResponses = trainers.stream()
                .map(trainer -> new Update2Response(
                        trainer.getUser().getFirstName(),
                        trainer.getUser().getLastName(),
                        trainer.getUser().getUsername(),
                        trainer.getTrainingType().getName()
                ))
                .collect(Collectors.toList());
        return new Update2Response(updateResponses);
    }
}
