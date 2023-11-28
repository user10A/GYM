package gym.controller;
import gym.dto.SimpleResponse;
import gym.dto.TrainingRequest;
import gym.model.Training;
import gym.service.TrainingService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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
    public ResponseEntity<SimpleResponse> addTraining(@RequestBody TrainingRequest addTrainingRequest) {
        trainingService.addTraining(addTrainingRequest);
        return new ResponseEntity<>(HttpStatus.OK);
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
