package gym.service;

import gym.dto.SimpleResponse;
import gym.dto.TrainingRequest;
import gym.model.Training;
import gym.model.TrainingType;
import org.springframework.data.repository.query.Param;
import org.springframework.http.ResponseEntity;

import java.time.LocalDate;
import java.util.List;

public interface TrainingService {
    List<Training> getAllTraining();
    ResponseEntity<SimpleResponse> addTraining(TrainingRequest addTrainingRequest);
    SimpleResponse save(Training training);

    String update(long id, TrainingRequest training);

    Training getByTraineeName(String name);
    Training getByTrainerName(String name);
    Training getByDurationAndDate(int moth ,LocalDate date);
    SimpleResponse delete(long id);
    Training findByTrainingName(String name);

}
