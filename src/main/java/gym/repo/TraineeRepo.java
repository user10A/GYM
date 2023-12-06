package gym.repo;

import gym.dto.trainee.TraineeResponse;
import gym.model.Trainee;
import gym.model.Trainer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;


import java.util.List;
import java.util.Optional;

@Repository
public interface TraineeRepo extends JpaRepository<Trainee, Long> {

    @Query("select trainee.id, trainee.address, trainee.dateOfBirth, trainee.user.email from Trainee trainee")
    List<TraineeResponse> findAllTrainees();
    @Query("SELECT t FROM Trainee t JOIN t.user u WHERE u.email = :email")
    Trainee findTraineeByEmail(@Param("email") String email);
    @Modifying
    @Query("UPDATE User u SET u.isActive = :isActive WHERE u.email = :email")
    void updateIsActivityByEmail(@Param("email") String email, @Param("isActive") boolean isActive);
    @Modifying
    @Query("UPDATE User u SET u.password = :password WHERE u.email = :email")
    void updatePasswordTrainee(@Param("email") String email, @Param("password") String password);

    @Query("Select u  from User u where u.email = :email and  u.password = :password")
    void traineePasswordChange(@Param("email") String email ,@Param("password")String password);
    @Query("select t  from Trainer t")
    List<Trainer>getAllByTraineeTrainers();
    @Query("SELECT t FROM Trainee t JOIN t.user u WHERE u.email = :email")
    Trainee findTraineeByUserUsername(@Param("email") String email);
}
