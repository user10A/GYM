package gym.service.Impl;
import gym.dto.SimpleResponse;
import gym.model.TrainingType;
import gym.repo.TrainingTypeRepo;
import gym.service.TrainingTypeService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Service
@Transactional
public class TrainingTypeServiceImpl implements TrainingTypeService {
    @Autowired
    private TrainingTypeRepo trainingTypeRepo;

    @Override
    public List<TrainingType> getAllTrainingType() {
        return trainingTypeRepo.findAll();
    }

    @Override
    public SimpleResponse save(TrainingType trainingType) {
         trainingTypeRepo.save(trainingType);
         return new SimpleResponse("TrainingType successfully saving", HttpStatus.OK);
    }

    @Override
    public String update(long id, TrainingType trainingType) {
        TrainingType trainingType1 = trainingTypeRepo.findById(id).orElseThrow(()-> new NoSuchElementException("TrainingType not found by id"+id));
        trainingType1.setName(trainingType.getName());
        trainingTypeRepo.save(trainingType1);
        return "TrainingType successfully updated";
    }

    @Override
    public TrainingType getByName(String name) {
        return trainingTypeRepo.getByName(name);
    }

    @Override
    public SimpleResponse delete(long id) {
        trainingTypeRepo.deleteById(id);
        return new SimpleResponse("Training Type successfully deleted",HttpStatus.OK);
    }
}
