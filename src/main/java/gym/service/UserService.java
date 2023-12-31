package gym.service;
import gym.model.User;

import java.util.List;
import java.util.Optional;

public interface UserService {

    String generateUsername(String first_name, String last_name);
    String generatePassword();

    boolean usernameExists(String username);

    List<User> getAll();

    void save(User user);
    Optional<User> getUserByEmail(String email);
    boolean existsByEmail(String email);
}
