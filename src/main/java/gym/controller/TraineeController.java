package gym.controller;

import gym.dto.trainee.*;
import gym.dto.user.Login;
import gym.dto.user.LoginChange;
import gym.dto.user.SimpleResponse;
import gym.dto.user.UserDto;
import gym.repo.TraineeRepo;
import gym.service.TraineeService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/trainee")
@RequiredArgsConstructor
@Transactional
public class TraineeController {
    private final TraineeService traineeService;
    private final TraineeRepo traineeRepo;

    @GetMapping
    public List<TraineeResponse> getAllCustomers(){
        return traineeService.getAllCustomers();
    }

    @PostMapping("/create")
    public UserDto save(@RequestBody TraineeRequest trainee){
        return traineeService.saveCustomer(trainee);
    }

    @DeleteMapping("/delete")
    public SimpleResponse delete(@RequestParam("userName") String userName){
        return traineeService.delete(userName);
    }

    @PutMapping("/update")
    public TraineeResponse update(@RequestBody UpdateRequest trainee){
        return traineeService.update(trainee);
    }

    @GetMapping("/get")
    public TraineeResponse getByUserName(@RequestParam String userName){
        return traineeService.getByUserName(userName);
    }
    @PutMapping("isActivity")
    public Login updateIsActivityByUserName(@RequestParam("userName") String userName, @RequestParam("isActive") boolean isActive){
        return traineeService.updateIsActivityOrDeActiveByUserName(userName,isActive);
    }
    @PutMapping("update-password")
    public SimpleResponse updatePasswordByUserName(@RequestBody LoginChange loginChange) {
        String username = loginChange.getUsername();
        String newPassword = loginChange.getNewPassword();
        traineeRepo.updatePasswordTrainee(username, newPassword);

        return new SimpleResponse("200 OK", HttpStatus.OK);
    }

    @PutMapping("/update-trainers")
    public ResponseEntity<Update2Response> updateTrainersList(
            @RequestBody @Valid UpdateRequest2 updateRequest
    ) {
        Update2Response response = traineeService.updateTrainersList( updateRequest);
        return ResponseEntity.ok(response);
    }
    @GetMapping("login")
    public SimpleResponse login (@RequestParam("userName") String userName, @RequestParam("password")String password){
        return traineeService.logonTrainee(userName,password);
    }
}
