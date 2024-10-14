package dev.yassiraitelghari.services;

import dev.yassiraitelghari.domain.User;
import dev.yassiraitelghari.repositories.UserRepository;
import dev.yassiraitelghari.repositories.UserRepositoryImp;
import dev.yassiraitelghari.utils.RegisterValidationMessages;

import java.util.List;
import java.util.Optional;
import org.mindrot.jbcrypt.BCrypt;

public class UserServiceImp implements UserService {
    private UserRepository userRepository = new UserRepositoryImp();

    @Override
    public User add(String username, String email, String name, String lastName, String password, String role) {
        RegisterValidationMessages.getRegisterValidationMessage().resetAll();
        RegisterValidationMessages.getRegisterValidationMessage().validateAll(username, email, name, lastName, password, role);
        RegisterValidationMessages validation = RegisterValidationMessages.getRegisterValidationMessage();
        if (validation.allNull()) {
            User user = new User();
            user.setUsername(username);
            user.setName(name);
            user.setPassword(BCrypt.hashpw(password, BCrypt.gensalt()));
            user.setEmail(email);
            user.setRole(role);
            user.setLastName(lastName);
            user.setReplaceJeton(2);
            user.setDeleteJeton(1);
            return userRepository.add(user);
        } else {
            return null;
        }
    }

    @Override
    public Optional<User> get(String email) {
        return userRepository.get(email);
    }

    @Override
    public User update(User user) {
        return userRepository.update(user);
    }

    @Override
    public boolean delete(User user) {
        return userRepository.delete(user);
    }

    @Override
    public List<User> getAll() {
        return userRepository.getAll();
    }

    @Override
    public User findById(int id) {
        return userRepository.findById(id);
    }

    @Override
    public boolean updateReplaceToken() {
        return userRepository.updateReplaceToken();
    }

    @Override
    public boolean updateDeleteToken() {
        return userRepository.updateDeleteToken();
    }

    @Override
    public double successPercentage(User user) {
        if (user.getTasks().isEmpty()) {
            return -1;
        } else {
            int validatedTasksCount = user.getTasks().stream().filter(task -> task.getStatus().equals("Validated")).toList().size();
            int totalTasks = user.getTasks().size();
            return (double) (validatedTasksCount * 100) / totalTasks;
        }
    }

    @Override
    public Optional<User> getByUsername(String username) {
        return userRepository.getByUsername(username);
    }

}