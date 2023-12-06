package gym.service.Impl;
import gym.config.jwt.JwtService;
import gym.dto.trainee.TrainerUpdateRequest;
import gym.dto.trainer.TrainerProfileRes;
import gym.dto.trainer.TrainerRequest;
import gym.dto.trainer.TrainerRequest2;
import gym.dto.trainer.TrainerResponse;
import gym.dto.user.*;
import gym.enums.Role;
import gym.model.Trainer;
import gym.model.TrainingType;
import gym.model.User;
import gym.repo.TrainerRepo;
import gym.repo.TrainingTypeRepo;
import gym.repo.UserRepo;
import gym.service.TrainerService;
import gym.service.UserService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Service
@Transactional
@RequiredArgsConstructor
public class TrainerServiceImpl implements TrainerService {
    private final TrainerRepo trainerRepo;
    private final UserRepo userRepository;
    private final TrainingTypeRepo trainingTypeRepo;
    private final JwtService jwtService;
    private final PasswordEncoder passwordEncoder;

    @Override
    public List<TrainerResponse> getAllTrainers() {
        return trainerRepo.findAllTrainers();
    }

    @Override
    public AuthenticationResponse signUp(TrainerRequest2 trainerRequest) {
        User user = new User();
        user.setFirstName(trainerRequest.getFirstName());
        user.setLastName(trainerRequest.getLastName());
        user.setPassword(passwordEncoder.encode(trainerRequest.getPassword()));
        user.setRole(Role.TRAINER);
        user.setEmail(trainerRequest.getEmail());
        userRepository.save(user);
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

        String token = jwtService.generateToken(user);
        return AuthenticationResponse.builder().
                token(token)
                .email(user.getEmail())
                .password(trainerRequest.getPassword())
                .role(user.getRole())
                .build();
    }

    @Override
    public TrainerProfileRes update(TrainerUpdateRequest trainer) {
        Trainer trainer1 = trainerRepo.findTrainerByEmail(trainer.getEmail());
        User user = trainer1.getUser();
        user.setEmail(trainer.getEmail());
        user.setFirstName(trainer.getFirstName());
        user.setLastName(trainer.getLastName());
        user.setActive(trainer.getIsActive());
        trainerRepo.save(trainer1);
        return new TrainerProfileRes(
                trainer1.getUser().getEmail(),
                trainer1.getUser().getFirstName(),
                trainer1.getUser().getLastName(),
                trainer1.getUser().isActive(),
                trainer1.getTrainerTrainees(),
                trainer1.getTrainingType().getName()
                );
    }

        @Override
    public TrainerResponse getByEmail(String email) {
       Trainer trainer = trainerRepo.findTrainerByEmail(email);
       return new TrainerResponse(
               trainer.getId(),
               trainer.getUser().getFirstName(),
               trainer.getUser().getLastName(),
               trainer.getTrainingType(),
               trainer.getUser().getEmail(),
               trainer.getUser().getPassword(),
               trainer.getUser().isActive()
       );
    }


    @Override
    public SimpleResponse delete(String email) {
        Trainer trainer = trainerRepo.findTrainerByEmail(email);
        if (trainer!=null) {
            trainerRepo.delete(trainer);
            return new SimpleResponse("Trainer successfully deleted", HttpStatus.OK);
        }else {
            return new SimpleResponse("Trainer by userName: "+email+" not found",HttpStatus.NOT_FOUND);
        }
    }

    @Override
    public Login updateIsActivityOrDeActiveByUserName(String email, boolean isActive) {
        Trainer trainer = trainerRepo.findTrainerByEmail(email);
        trainerRepo.updateIsActivityByEmail(email, isActive);
        trainerRepo.save(trainer);
        return new Login(trainer.getUser().getEmail(),trainer.getUser().isActive());
    }

    @Override
    public SimpleResponse updatePasswordTrainer(LoginChange change) {
            trainerRepo.updatePasswordTrainer(change.getEmail(),change.getNewPassword());
        return new SimpleResponse("Trainer password successfully updated", HttpStatus.OK);    }

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
    public List<TrainingType> getAllTrainingType() {
        return trainingTypeRepo.getAllTrainingTypes();
    }
}
