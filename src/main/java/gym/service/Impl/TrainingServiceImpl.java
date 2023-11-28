package gym.service.Impl;

import gym.dto.SimpleResponse;
import gym.dto.TrainingRequest;
import gym.model.Trainee;
import gym.model.Trainer;
import gym.model.Training;
import gym.model.TrainingType;
import gym.repo.TraineeRepo;
import gym.repo.TrainerRepo;
import gym.repo.TrainingRepo;
import gym.repo.TrainingTypeRepo;
import gym.service.TrainingService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.NoSuchElementException;

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
    public ResponseEntity<SimpleResponse> addTraining(TrainingRequest addTrainingRequest) {
        Trainee trainee = traineeRepo.findTraineeByUsername(addTrainingRequest.getTraineeName());
        Trainer trainer = trainerRepo.findTrainerByUsername(addTrainingRequest.getTrainerName());
        TrainingType trainingType = trainingTypeRepo.getByName(addTrainingRequest.getTrainingTypeName());
        Training training = new Training();
        training.setTrainee(trainee);
        training.setTrainer(trainer);
        training.setTrainingType(trainingType);
        training.setTrainingName(addTrainingRequest.getTrainingName());
        training.setDate(addTrainingRequest.getDate());
        trainingRepo.save(training);
        return new ResponseEntity<>(HttpStatus.OK);
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
        TrainingType trainingType = trainingTypeRepo.getByName(training.getTrainingTypeName());
        Training training1 =trainingRepo.findById(id).orElseThrow(()-> new NoSuchElementException("Training not found by id"+id));
        training1.setTrainingName(training.getTrainingName());
        training1.setDate(training.getDate());
        training1.setTrainingType(trainingType);
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
}
