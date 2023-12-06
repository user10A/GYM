package gym.dto.trainee;


import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class UpdateRequest2 {

    private List<String> emails;
    private String traineeEmail;


}
