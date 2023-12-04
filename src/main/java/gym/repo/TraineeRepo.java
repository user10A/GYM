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

    @Query("select trainee.id, trainee.address, trainee.dateOfBirth, trainee.user.userName from Trainee trainee")
    List<TraineeResponse> findAllTrainees();
    @Query("SELECT t FROM Trainee t JOIN t.user u WHERE u.userName = :username")
    Trainee findTraineeByUsername(@Param("username") String username);
    @Modifying
    @Query("UPDATE User u SET u.isActive = :isActive WHERE u.userName = :userName")
    void updateIsActivityByUserName(@Param("userName") String userName, @Param("isActive") boolean isActive);
    @Modifying
    @Query("UPDATE User u SET u.password = :password WHERE u.userName = :userName")
    void updatePasswordTrainee(@Param("userName") String userName, @Param("password") String password);

    @Query("Select u  from User u where u.userName = :userName and  u.password = :password")
    void traineePasswordChange(@Param("userName") String userName ,@Param("password")String password);
//    @Query("update Trainee t set t.user.firstName, t.user.lastName, t   ")
//    List<Trainee> findAllByUserNames (@Param("userNames")List<String>userNames);
    @Query("select t  from Trainer t")
    List<Trainer>getAllByTraineeTrainers();
    @Query("SELECT t FROM Trainee t JOIN t.user u WHERE u.userName = :userName")
    Trainee findTraineeByUserUsername(@Param("userName") String userName);
}
