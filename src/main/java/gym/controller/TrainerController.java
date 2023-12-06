package gym.controller;
import gym.dto.trainee.TrainerUpdateRequest;
import gym.dto.trainer.TrainerProfileRes;
import gym.dto.trainer.TrainerRequest;
import gym.dto.trainer.TrainerRequest2;
import gym.dto.trainer.TrainerResponse;
import gym.dto.user.*;
import gym.model.TrainingType;
import gym.repo.TrainerRepo;
import gym.service.TrainerService;
import jakarta.annotation.security.PermitAll;
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
    @PermitAll
    @PostMapping("/signUp")
    public AuthenticationResponse signUp(@RequestBody TrainerRequest2 trainer){
        return trainerService.signUp(trainer);
    }


    @DeleteMapping("/delete")
    public SimpleResponse delete(@RequestParam String email){
        return trainerService.delete(email);
    }

    @PutMapping("/update")
    public TrainerProfileRes update(@RequestBody TrainerUpdateRequest trainerRequest){
       return trainerService.update(trainerRequest);
    }

    @GetMapping("/get")
    public TrainerResponse getTrainerByEmail(@RequestParam String username){
        return trainerService.getByEmail(username);
    }

    @PostMapping("isActivity")
    public Login updateIsActivityByUserName(@RequestParam("email") String userName, @RequestParam("isActive") boolean isActive){
        return trainerService.updateIsActivityOrDeActiveByUserName(userName,isActive);
    }
    @PostMapping("update-password")
    public SimpleResponse updatePasswordByUserName(@RequestParam LoginChange loginChange){
        String username = loginChange.getEmail();
        String newPassword = loginChange.getNewPassword();
        trainerRepo.updatePasswordTrainer(username, newPassword);
        return new SimpleResponse("200 OK", HttpStatus.OK);
    }
    @PermitAll
    @GetMapping("/signIn")
    public AuthenticationResponse signIn(@RequestBody UserCheckRequest request){
        return trainerService.signIn(request);
    }
    @GetMapping("trainingTypes")
    public List<TrainingType> getAllTrainingTypes(){
        return trainerService.getAllTrainingType();
    }
}
