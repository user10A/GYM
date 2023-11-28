package gym.repo;
import gym.dto.TraineeResponse;
import gym.dto.TrainerRequest;
import gym.dto.TrainerResponse;
import gym.model.Trainee;
import gym.model.Trainer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TrainerRepo extends JpaRepository<Trainer,Long> {
    @Query("select trainer.id,trainer.user.firstName,trainer.user.lastName, trainer.trainingType from Trainer trainer")
    List<TrainerResponse> findAllTrainers();
    @Query("SELECT t FROM Trainer t JOIN t.user u WHERE u.userName = :userName")
    Trainer findTrainerByUsername(@Param("userName") String userName);
    @Modifying
    @Query("UPDATE User u SET u.isActive = :isActive WHERE u.userName = :userName")
    void updateIsActivityByUserName(@Param("userName") String userName, @Param("isActive") boolean isActive);

    @Modifying
    @Query("UPDATE User u SET u.password = :password WHERE u.userName = :userName")
    void updatePasswordTrainer(@Param("userName") String userName ,@Param("password")String password);
}
