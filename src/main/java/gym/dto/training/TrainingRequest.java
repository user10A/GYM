package gym.dto.training;
import lombok.Data;
import java.time.LocalDate;

@Data
public class TrainingRequest {
    private String trainingName;
    private LocalDate date;
    private String traineeEmail;
    private String trainerEmail;
}
