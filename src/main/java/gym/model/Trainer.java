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
    @OneToOne(cascade = CascadeType.ALL)
    private Specialization specialization;
    @OneToOne (fetch = FetchType.LAZY, cascade = CascadeType.ALL) @JoinColumn (name = "user_id")
    private User user;
    @ManyToMany
    private List<Trainee>trainerTrainees;
    public Trainer() {
    }
}
