package gym.repo;

import gym.model.TrainingType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TrainingTypeRepo extends JpaRepository<TrainingType,Long> {
    @Query("select t.id,t.name from TrainingType t where t.name =: name")
    TrainingType getByName(@Param("name")String name);
    @Query("select t from TrainingType t ")
    List<TrainingType> getAllTrainingTypes();
}
