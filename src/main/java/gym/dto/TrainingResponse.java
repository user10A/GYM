package gym.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
@Getter
@Setter
@NoArgsConstructor
public class TrainingResponse {
    private Long id;
    private String trainingName;
    private LocalDate start;
    private LocalDate date;
    private int duration;
    private Long traineeId;
    private Long trainerId;
    private Long trainingTypeId;

    public TrainingResponse(Long id, LocalDate start, LocalDate date, int duration) {
        this.id = id;
        this.start = start;
        this.date = date;
        this.duration = duration;
    }

    public TrainingResponse(Long traineeId, Long trainerId, Long trainingTypeId) {
        this.traineeId = traineeId;
        this.trainerId = trainerId;
        this.trainingTypeId = trainingTypeId;
    }

    public TrainingResponse(Long id, LocalDate start, LocalDate date, int duration, Long traineeId, Long trainerId, Long trainingTypeId) {
        this.id = id;
        this.start = start;
        this.date = date;
        this.duration = duration;
        this.traineeId = traineeId;
        this.trainerId = trainerId;
        this.trainingTypeId = trainingTypeId;
    }
}
