package gym.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Training {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String trainingName;
    private LocalDate start;
    private LocalDate date;  // будет 2 метода эта дат окончания а, localdate(now )еще должен работать чтобы поставить старт даты
    private int duration; // moth будет определять  сколько месяцев месяц
    @OneToOne
    @JoinColumn(name ="trainee_id" )
    private Trainee trainee;
    @OneToOne
    @JoinColumn(name ="trainer_id" )
    private Trainer trainer;
    @OneToOne
    @JoinColumn(name ="trainingType_id" )
    private TrainingType trainingType ; //trainingType id; отношения


}
