package gym.controller;

import gym.dto.trainee.*;
import gym.dto.user.*;
import gym.repo.TraineeRepo;
import gym.service.TraineeService;
import jakarta.annotation.security.PermitAll;
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
    public List<TraineeResponse> getAllTrainee() {
        return traineeService.getAllTrainee();
    }

    @PermitAll
    @PostMapping("/signUp")
    public AuthenticationResponse signUp(@RequestBody TraineeRequest2 trainee) {
        return traineeService.signUp(trainee);
    }

    @DeleteMapping("/delete")
    public SimpleResponse delete(@RequestParam("email") String email) {
        return traineeService.delete(email);
    }

    @PutMapping("/update")
    public TraineeResponse update(@RequestBody UpdateRequest trainee) {
        return traineeService.update(trainee);
    }

    @GetMapping("/get")
    public TraineeResponse getTraineeByEmail(@RequestParam String email) {
        return traineeService.getByEmail(email);
    }

    @PutMapping("isActivity")
    public Login updateIsActivityByUserName(@RequestParam("email") String email, @RequestParam("isActive") boolean isActive) {
        return traineeService.updateIsActivityOrDeActiveByUserName(email, isActive);
    }

    @PutMapping("update-password")
    public SimpleResponse updatePasswordByUserName(@RequestBody LoginChange loginChange) {
        String email = loginChange.getEmail();
        String newPassword = loginChange.getNewPassword();
        traineeRepo.updatePasswordTrainee(email, newPassword);

        return new SimpleResponse("200 OK", HttpStatus.OK);
    }

    @PutMapping("/update-trainers")
    public ResponseEntity<Update2Response> updateTrainersList(
            @RequestBody @Valid UpdateRequest2 updateRequest
    ) {
        Update2Response response = traineeService.updateTrainersList(updateRequest);
        return ResponseEntity.ok(response);
    }

    @PermitAll
    @GetMapping("signIn")
    public AuthenticationResponse signIn(@RequestBody UserCheckRequest loginChange) {
        return traineeService.signIn(loginChange);
    }
}
