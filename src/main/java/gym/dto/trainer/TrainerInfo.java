package gym.dto.trainer;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TrainerInfo {
        private String firstName;
        private String lastName;
        private String email;
        private String trainingType;

        public TrainerInfo(String firstName, String lastName, String email, String trainingType) {
            this.firstName = firstName;
             this.lastName = lastName;
            this.email = email;
            this.trainingType = trainingType;
        }
    }

