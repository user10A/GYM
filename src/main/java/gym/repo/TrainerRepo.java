package gym.repo;
import gym.dto.trainer.TrainerResponse;
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
    @Query("SELECT t FROM Trainer t JOIN t.user u WHERE u.email = :email")
    Trainer findTrainerByEmail(@Param("email") String email);
    @Modifying
    @Query("UPDATE User u SET u.isActive = :isActive WHERE u.email = :email")
    void updateIsActivityByEmail(@Param("email") String email, @Param("isActive") boolean isActive);

    @Modifying
    @Query("UPDATE User u SET u.password = :password WHERE u.email = :email")
    void updatePasswordTrainer(@Param("email") String email ,@Param("password")String password);
    @Query("Select u  from Trainee u where u.user.email = :email and  u.user.password = :password")
    Trainer trainerPasswordChange(@Param("email") String email ,@Param("password")String password);
//    @Query("select t  from Trainer t")
//    List<Trainer>getAllByTraineeTrainers();
    @Query("select t from Trainer t join t.user u where u.email in :emails")
    List<Trainer> findByTrainerUserNameIn(@Param("emails") List<String> emails);
    @Query("SELECT t FROM Trainer t WHERE t.user.isActive = false ")
    List<Trainer> findActiveTrainer();
}
