package gym.dto.user;

import gym.enums.Role;
import lombok.Builder;

@Builder
public record AuthenticationResponse(
        String email,
        String token,
        String password,
        Role role
) {
}
