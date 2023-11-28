package gym.repo;

import gym.dto.TraineeResponse;
import gym.model.Trainee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;


import java.util.List;

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
    void updatePasswordTrainee(@Param("userName") String userName ,@Param("password")String password);
}
