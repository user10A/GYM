package gym.dto.user;

import gym.enums.Role;
import lombok.Builder;

@Builder
public record AuthenticationResponse(
        String userName,
        String token,
        String password,
        Role role
) {
}
