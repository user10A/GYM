package gym.controller;

import gym.dto.*;
import gym.service.TrainerService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/trainer")
@RequiredArgsConstructor
public class TrainerController {
    private final TrainerService trainerService;

    @GetMapping
    public List<TrainerResponse> getAllCustomers(){
        return trainerService.getAllTrainers();
    }


    @PostMapping("/create")
    public UserDto save(@RequestBody TrainerRequest customer){
        return trainerService.saveCustomer(customer);
    }


    @DeleteMapping("/{id}")
    public SimpleResponse delete(@PathVariable  long id){
        return trainerService.delete(id);
    }

    @PutMapping("/update/{id}")
    public void update(@PathVariable long id,@RequestBody TrainerRequest customer){
        trainerService.update(id,customer);
    }

    @GetMapping("/get")
    public TrainerResponse getByUserName(@RequestParam String username){
        return trainerService.getByUserName(username);
    }


    @PostMapping("isActivity")
    public SimpleResponse updateIsActivityByUserName(@RequestParam("userName") String userName, @RequestParam("isActive") boolean isActive){
        return trainerService.updateIsActivityOrDeActiveByUserName(userName,isActive);
    }
    @PostMapping("password")
    public SimpleResponse updatePasswordByUserName(@RequestParam("userName") String userName, @RequestParam("password")String password){
        return trainerService.updatePasswordTrainer(userName,password);

    }
}
