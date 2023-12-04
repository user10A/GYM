package gym.service;

import gym.dto.trainer.TrainerProfileRes2;
import gym.dto.training.FreeRequest;
import gym.dto.training.TrainingResponse;
import gym.dto.user.SimpleResponse;
import gym.dto.training.TrainingRequest;
import gym.model.Training;

import java.time.LocalDate;
import java.util.List;

public interface TrainingService {
    List<Training> getAllTraining();
    SimpleResponse addTraining(TrainingRequest addTrainingRequest);
    SimpleResponse save(Training training);

    String update(long id, TrainingRequest training);

    Training getByTraineeName(String name);
    Training getByTrainerName(String name);
    Training getByDurationAndDate(int moth ,LocalDate date);
    SimpleResponse delete(long id);
    Training findByTrainingName(String name);
    List<TrainerProfileRes2> getNotAssignedTrainers(FreeRequest freeRequest);
}
