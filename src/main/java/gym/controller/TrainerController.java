package gym.controller;
import gym.dto.trainee.TrainerUpdateRequest;
import gym.dto.trainer.TrainerProfileRes;
import gym.dto.trainer.TrainerRequest;
import gym.dto.trainer.TrainerResponse;
import gym.dto.user.Login;
import gym.dto.user.LoginChange;
import gym.dto.user.SimpleResponse;
import gym.dto.user.UserDto;
import gym.model.TrainingType;
import gym.repo.TrainerRepo;
import gym.service.TrainerService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/trainer")
@RequiredArgsConstructor
@Transactional
public class TrainerController {
    private final TrainerService trainerService;
    private final TrainerRepo trainerRepo;

    @GetMapping
    public List<TrainerResponse> getAllCustomers(){
        return trainerService.getAllTrainers();
    }
    @PostMapping("/create")
    public UserDto save(@RequestBody TrainerRequest customer){
        return trainerService.saveCustomer(customer);
    }


    @DeleteMapping("/delete")
    public SimpleResponse delete(@RequestParam String userName){
        return trainerService.delete(userName);
    }

    @PutMapping("/update")
    public TrainerProfileRes update(@RequestBody TrainerUpdateRequest trainerRequest){
       return trainerService.update(trainerRequest);
    }

    @GetMapping("/get")
    public TrainerResponse getByUserName(@RequestParam String username){
        return trainerService.getByUserName(username);
    }

    @PostMapping("isActivity")
    public Login updateIsActivityByUserName(@RequestParam("userName") String userName, @RequestParam("isActive") boolean isActive){
        return trainerService.updateIsActivityOrDeActiveByUserName(userName,isActive);
    }
    @PostMapping("update-password")
    public SimpleResponse updatePasswordByUserName(@RequestParam LoginChange loginChange){
        String username = loginChange.getUsername();
        String newPassword = loginChange.getNewPassword();
        trainerRepo.updatePasswordTrainer(username, newPassword);
        return new SimpleResponse("200 OK", HttpStatus.OK);
    }
    @GetMapping("/check-credentials")
    public SimpleResponse login(@RequestParam("userName") String userName, @RequestParam("password")String password){
        return trainerService.loginTrainee(userName,password);
    }
    @GetMapping("trainingTypes")
    public List<TrainingType> getAllTrainingTypes(){
        return trainerService.getAllTrainingType();
    }
}
