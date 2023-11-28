package gym.controller;
import gym.dto.SimpleResponse;
import gym.model.TrainingType;
import gym.service.TrainingTypeService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("trainingType")
@RequiredArgsConstructor
public class TrainingTypeApi {

    @Autowired
    private TrainingTypeService trainingTypeService;

    @GetMapping
    public List<TrainingType> getAllTrainers(){
        return trainingTypeService.getAllTrainingType();
    }

    @PostMapping("/create")
    public SimpleResponse save(@RequestBody TrainingType trainingType){
        return trainingTypeService.save(trainingType);
    }
    @DeleteMapping("/{id}")
    public SimpleResponse delete(@PathVariable  long id){
        return trainingTypeService.delete(id);
    }

    @PutMapping("/update/{id}")
    public void update(@PathVariable long id,@RequestBody TrainingType trainingType){
        trainingTypeService.update(id,trainingType);
    }

    @GetMapping("/get")
    public TrainingType getByUserName(@RequestParam String name){
        return trainingTypeService.getByName(name);
    }


}
