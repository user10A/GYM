package gym.service.Impl;


import gym.dto.SimpleResponse;
import gym.model.User;
import gym.repo.UserRepo;
import gym.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Random;
import java.util.concurrent.atomic.AtomicBoolean;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepo userRepo;
    @Override
    public String generateUsername(String first_name, String last_name) {
        String baseUsername = first_name + "." + last_name;
        String username = baseUsername;
        int serialNumber = 1;

        while (usernameExists(username)) {
            serialNumber++;
            username = baseUsername + "." + serialNumber;
        }
        return username;
    }

    @Override
    public String generatePassword() {
        String characters = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
        StringBuilder password = new StringBuilder();
        Random random = new Random();

        for (int i = 0; i < 10; i++) {
            password.append(characters.charAt(random.nextInt(characters.length())));
        }

        return password.toString();
    }

    @Override
    public boolean usernameExists(String username) {
        AtomicBoolean exists = new AtomicBoolean(false);
        getAll().forEach(user -> {
            if (user.getUserName() != null && user.getUserName().equals(username)) {
                exists.set(true);
            }
        });
        return exists.get();
    }

    @Override
    public List<User> getAll() {
        return userRepo.findAll();
    }

    @Override
    public void save(User user) {
        userRepo.save(user);
    }



}
