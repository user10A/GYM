package gym.controller;

import gym.dto.trainer.TrainerProfileRes2;
import gym.dto.training.FreeRequest;
import gym.dto.training.TrainingRequest;
import gym.dto.user.SimpleResponse;
import gym.model.Training;
import gym.service.TrainingService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/training")
@RequiredArgsConstructor
public class TrainingApi {
    @Autowired
    private TrainingService trainingService;

    @GetMapping
    public List<Training> getAllTrainings(){
        return trainingService.getAllTraining();
    }
    @PostMapping("/add")
    public SimpleResponse addTraining(@RequestBody TrainingRequest addTrainingRequest) {
        return  trainingService.addTraining(addTrainingRequest);
    }
    @DeleteMapping("/{id}")
    public SimpleResponse delete(@PathVariable  long id){
        return trainingService.delete(id);
    }

    @PutMapping("/update/{id}")
    public void update(@PathVariable long id,@RequestBody TrainingRequest training){
        trainingService.update(id,training);
    }

    @GetMapping("/get")
    public Training getByUserName(@RequestParam String name){
        return trainingService.findByTrainingName(name);
    }

    @GetMapping("/userName")
    public ResponseEntity<List<TrainerProfileRes2>> getNotAssignedTrainers(@RequestBody FreeRequest freeRequest) {
        List<TrainerProfileRes2> notAssignedTrainers = trainingService.getNotAssignedTrainers(freeRequest);
        return ResponseEntity.ok(notAssignedTrainers);
    }

}
