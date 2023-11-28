package gym.controller;
import gym.dto.SimpleResponse;
import gym.dto.TrainingRequest;
import gym.model.Training;
import gym.service.TrainingService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
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
    @PostMapping("/create")
    public void add(@RequestBody TrainingRequest trainingRequest){
        trainingService.addTraining(trainingRequest);
    }

    @PostMapping("/save")
    public SimpleResponse save(@RequestBody Training training){
        return trainingService.save(training);
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
}
