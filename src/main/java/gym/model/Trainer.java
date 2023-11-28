package gym.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Entity
@Data
@Builder
@AllArgsConstructor
public class Trainer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @OneToOne
    @JoinColumn (name = "trainingType_id")
    private TrainingType trainingType;
    @OneToOne (fetch = FetchType.LAZY) @JoinColumn (name = "user_id")
    private User user;
    @ManyToMany
    private List<Trainee>trainerTrainees;
    public Trainer() {
    }
}
