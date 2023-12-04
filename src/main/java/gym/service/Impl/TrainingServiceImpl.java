package gym.service.Impl;
import gym.dto.trainer.TrainerProfileRes2;
import gym.dto.training.FreeRequest;
import gym.dto.user.SimpleResponse;
import gym.dto.training.TrainingRequest;
import gym.model.Trainee;
import gym.model.Trainer;
import gym.model.Training;
import gym.repo.TraineeRepo;
import gym.repo.TrainerRepo;
import gym.repo.TrainingRepo;
import gym.repo.TrainingTypeRepo;
import gym.service.TrainingService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

@Service
@Transactional
public class TrainingServiceImpl implements TrainingService {
    @Autowired
    private TrainingRepo trainingRepo;
    @Autowired
    private TraineeRepo traineeRepo;
    @Autowired
    private TrainerRepo trainerRepo;
    @Autowired
    private TrainingTypeRepo trainingTypeRepo;
    @Override
    public List<Training> getAllTraining() {
        return trainingRepo.findAll();
    }

    @Override
    public SimpleResponse addTraining(TrainingRequest addTrainingRequest) {
        Trainee trainee = traineeRepo.findTraineeByUsername(addTrainingRequest.getTraineeName());
        Trainer trainer = trainerRepo.findTrainerByUsername(addTrainingRequest.getTrainerName());
        Training training = new Training();
        training.setTrainee(trainee);
        training.setTrainer(trainer);
        training.setTrainingName(addTrainingRequest.getTrainingName());
        training.setTrainingType(trainer.getTrainingType());
        // Установите дату начала тренировки на текущую дату
        LocalDate startDate = LocalDate.now();
        training.setStart(startDate);

        // Установите дату окончания тренировки
        LocalDate endDate = addTrainingRequest.getDate();
        training.setDate(endDate);

        // Вычислите продолжительность на основе даты начала и даты окончания
        int duration = (int) ChronoUnit.MONTHS.between(startDate, endDate);
        training.setDuration(duration);

        trainingRepo.save(training);
        return new SimpleResponse("Training successfully saving",HttpStatus.OK);
    }


    @Override
    public SimpleResponse save(Training training) {
         trainingRepo.save(training);
         return new SimpleResponse("Training successfully saving", HttpStatus.OK);
    }

    @Override
    public String update(long id, TrainingRequest training) {
        Trainee trainee = traineeRepo.findTraineeByUsername(training.getTraineeName());
        Trainer trainer = trainerRepo.findTrainerByUsername(training.getTrainerName());
        Training training1 =trainingRepo.findById(id).orElseThrow(()-> new NoSuchElementException("Training not found by id"+id));
        training1.setTrainingName(training.getTrainingName());
        training1.setDate(training.getDate());
        training1.setTrainer(trainer);
        training1.setTrainee(trainee);
        trainingRepo.save(training1);
        return "Training successfully updated";
    }

    @Override
    public Training getByTraineeName(String name) {
        return trainingRepo.findTraineeByUsername(name);
    }

    @Override
    public Training getByTrainerName(String name) {
        return trainingRepo.findTrainerByUsername(name);
    }

    @Override
    public Training getByDurationAndDate(int months, LocalDate date) {
        return trainingRepo.findTrainingByDurationAndDate(months,date);
    }

    @Override
    public SimpleResponse delete(long id) {
        trainingRepo.deleteById(id);
        return new SimpleResponse("training successfully deleted",HttpStatus.OK);
    }

    @Override
    public Training findByTrainingName(String name) {
        return trainingRepo.findByTrainingName(name);
    }

    @Override
    public List<TrainerProfileRes2> getNotAssignedTrainers(FreeRequest freeRequest) {
        String traineeUsername = freeRequest.getUserName();
        Trainee trainee = traineeRepo.findTraineeByUserUsername(traineeUsername);
        List<Trainer> trainers = trainerRepo.findActiveTrainer();


        List<Trainer> assignedTrainers = trainee.getTraineeTrainers();

        List<Trainer> notAssigned = trainers.stream()
                .filter(trainer -> !assignedTrainers.contains(trainer))
                .collect(Collectors.toList());

        return notAssigned.stream().map(
                trainer -> new TrainerProfileRes2(
                        trainer.getUser().getUsername(),
                        trainer.getUser().getFirstName(),
                        trainer.getUser().getLastName(),
                        trainer.getTrainingType().getName(),
                        trainer.getUser().isActive()
                )
        ).collect(Collectors.toList());
    }
}
