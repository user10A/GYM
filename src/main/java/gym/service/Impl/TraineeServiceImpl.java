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
import gym.repo.UserRepo;
import gym.service.TraineeService;
import gym.service.UserService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

@Service
@Transactional
@RequiredArgsConstructor
public class TraineeServiceImpl implements TraineeService {
    private final TraineeRepo traineeRepo;
    private final TrainerRepo trainerRepo;
    private final UserRepo userRepository;
    private final JwtService jwtService;
    private final PasswordEncoder passwordEncoder;
    @Override
    public List<TraineeResponse> getAllTrainee()
    {
        return traineeRepo.findAllTrainees();
    }

    @Override
    public AuthenticationResponse signUp(TraineeRequest2 traineeRequest) {
        User user = new User();
        user.setFirstName(traineeRequest.getFirstName());
        user.setLastName(traineeRequest.getLastName());
        user.setPassword(passwordEncoder.encode(traineeRequest.getPassword()));
        user.setRole(Role.TRAINEE);
        user.setEmail(traineeRequest.getEmail());
        userRepository.save(user);

        Trainee trainee1 = Trainee.builder()
                .dateOfBirth(traineeRequest.getDateOfBirth())
                .address(traineeRequest.getAddress())
                .user(user)
                .build();
        traineeRepo.save(trainee1);
        String token = jwtService.generateToken(user);
        return AuthenticationResponse.builder().
                token(token)
                .email(user.getEmail())
                .password(traineeRequest.getPassword())
                .role(user.getRole())
                .build();
    }

    @Override
    public TraineeResponse update(UpdateRequest trainee) {
        Trainee trainee1 = traineeRepo.findTraineeByEmail(trainee.getEmail());
        if (trainee1 == null) {
            throw new RuntimeException("Trainee not found with email: " + trainee.getEmail());
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
                trainee1.getUser().getEmail(),
                trainee1.getUser().getPassword(),
                trainee1.getAddress(),
                trainee1.getDateOfBirth(),
                trainee1.getUser().isActive());
    }


    @Override
    public TraineeResponse getByEmail(String userName) {
        Trainee trainee =traineeRepo.findTraineeByEmail(userName);
        return new TraineeResponse (
                trainee.getId(),
                trainee.getUser().getFirstName(),
                trainee.getUser().getLastName(),
                trainee.getUser().getEmail(),
                trainee.getUser().getPassword(),
                trainee.getAddress(),
                trainee.getDateOfBirth(),
                trainee.getUser().isActive()
        );
    }
    @Override
    public SimpleResponse delete(String email) {
        Trainee trainee = traineeRepo.findTraineeByEmail(email);
        if (trainee!=null){
            return new SimpleResponse("Trainee by email: " +email+ " successfully deleted", HttpStatus.OK);
        }else {
            return new SimpleResponse("Trainee by email"+ email + "not found ",HttpStatus.NOT_FOUND);
        }
    }

    @Override
    public Login updateIsActivityOrDeActiveByUserName(String email, boolean isActive) {
        Trainee trainee = traineeRepo.findTraineeByEmail(email);
        traineeRepo.updateIsActivityByEmail(email,isActive);
        traineeRepo.save(trainee);
        return new Login(trainee.getUser().getEmail(),trainee.getUser().isActive());
    }

    @Override
    public SimpleResponse updatePasswordTrainee(LoginChange change) {
        String oldPassword =change.getOldPassword();
        traineeRepo.updatePasswordTrainee(change.getEmail(), change.getNewPassword());
        return new SimpleResponse("Trainee password successfully updated", HttpStatus.OK);
    }

    @Override
    public AuthenticationResponse signIn(UserCheckRequest request) {
        User user = userRepository.getUserByEmail(request.getEmail()).orElseThrow(
                () -> new NoSuchElementException(
                        "user with email: " + request.getEmail() + " not fount"));
        if (request.getEmail().isBlank()) {
            throw new BadCredentialsException("email is blank");
        }
        if (!passwordEncoder.matches(request.getPassword(), user.getPassword())) {
            throw new BadCredentialsException("wrong password");
        }
        String jwtToken = jwtService.generateToken(user);
        return AuthenticationResponse.builder().
                token(jwtToken)
                .email(user.getEmail())
                .role(user.getRole())
                .password(user.getPassword())
                .build();
    }

    @Override
    public Update2Response updateTrainersList(UpdateRequest2 updateRequest) {
        Trainee trainee = traineeRepo.findTraineeByEmail(updateRequest.getTraineeEmail());

        List<String> trainersUsername = updateRequest.getEmails();
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
                        trainer.getUser().getEmail(),
                        trainer.getTrainingType().getName()
                ))
                .collect(Collectors.toList());
        return new Update2Response(updateResponses);
    }
}
