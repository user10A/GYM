package gym.service;

import gym.dto.user.SimpleResponse;
import gym.model.TrainingType;

import java.util.List;

public interface TrainingTypeService {
    List<TrainingType> getAllTrainingType();
    SimpleResponse save(TrainingType trainingType);

    String update(long id, TrainingType trainingType);

    TrainingType getByName(String name);

    SimpleResponse delete(long id);
}
