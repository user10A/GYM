package gym.controller;

import gym.dto.SimpleResponse;
import gym.dto.TraineeRequest;
import gym.dto.TraineeResponse;
import gym.dto.UserDto;
import gym.service.TraineeService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/trainee")
@RequiredArgsConstructor
public class TraineeController {
    private final TraineeService traineeService;

    @GetMapping
    public List<TraineeResponse> getAllCustomers(){
        return traineeService.getAllCustomers();
    }

    @PostMapping("/create")
    public UserDto save(@RequestBody TraineeRequest customer){
        return traineeService.saveCustomer(customer);
    }

    @DeleteMapping("/{id}")
    public SimpleResponse delete(@PathVariable  long id){
        return traineeService.delete(id);
    }

    @PutMapping("/update/{id}")
    public void update(@PathVariable long id,@RequestBody TraineeRequest customer){
        traineeService.update(id,customer);
    }

    @GetMapping("/get")
    public TraineeResponse getByUserName(@RequestParam String userName){
        return traineeService.getByUserName(userName);
    }
    @PostMapping("isActivity")
    public SimpleResponse updateIsActivityByUserName(@RequestParam("userName") String userName, @RequestParam("isActive") boolean isActive){
        return traineeService.updateIsActivityOrDeActiveByUserName(userName,isActive);
    }
    @PostMapping("password")
    public SimpleResponse updatePasswordByUserName(@RequestParam("userName") String userName, @RequestParam("password")String password){
        return traineeService.updatePasswordTrainee(userName,password);
    }

}
