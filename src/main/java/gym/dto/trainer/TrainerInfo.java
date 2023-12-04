package gym.dto.trainer;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TrainerInfo {
        private String firstName;
        private String lastName;
        private String userName;
        private String trainingType;

        public TrainerInfo(String firstName, String lastName, String userName, String trainingType) {
            this.firstName = firstName;
             this.lastName = lastName;
            this.userName = userName;
            this.trainingType = trainingType;
        }
    }

