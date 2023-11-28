package gym.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;
import java.util.List;

@Entity
@Data
@Builder
@AllArgsConstructor
public class Trainee {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id ;
    private String address;
    private LocalDate dateOfBirth;

    @OneToOne (fetch = FetchType.LAZY, cascade = CascadeType.ALL) @JoinColumn (name = "user_id")
    private User user;
    @ManyToMany(mappedBy = "trainerTrainees")
    List<Trainer>traineeTrainers;

    public Trainee() {

    }

    public Trainee(String address, LocalDate dateOfBirth, User user) {
        this.address = address;
        this.dateOfBirth = dateOfBirth;
        this.user = user;
    }

    @Override
    public String toString() {
        return "Trainee{" +
                "id=" + id +
                ", address='" + address + '\'' +
                ", dateOfBirth=" + dateOfBirth +
                ", user=" + user +
                '}';
    }
}
