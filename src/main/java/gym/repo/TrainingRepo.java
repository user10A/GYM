package gym.repo;

import gym.model.Training;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
@Repository
public interface TrainingRepo extends JpaRepository<Training,Long > {
    @Query("SELECT t FROM Training t JOIN t.trainee.user u WHERE u.email = :email")
    Training findTraineeByEmail(@Param("email") String email);
    @Query("SELECT t FROM Training t JOIN t.trainer.user u WHERE u.email = :email")
    Training findTrainerByEmail(@Param("email") String email);
    @Query("SELECT t FROM Training t WHERE t.duration = :duration AND t.date = :date")
    Training findTrainingByDurationAndDate(@Param("duration") int duration, @Param("date") LocalDate date);

    @Query("SELECT t FROM Training t  WHERE t.trainingName = :name")
    Training findByTrainingName(@Param("name") String name);


}