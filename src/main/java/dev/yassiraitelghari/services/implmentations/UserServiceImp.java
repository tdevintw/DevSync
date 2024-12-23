package dev.yassiraitelghari.services.implmentations;

import dev.yassiraitelghari.domain.User;
import dev.yassiraitelghari.exceptions.*;
import dev.yassiraitelghari.repositories.interfaces.UserRepository;
import dev.yassiraitelghari.repositories.implmentations.UserRepositoryImp;
import dev.yassiraitelghari.services.interfaces.UserService;
import dev.yassiraitelghari.utils.ProfileUpdateValidation;
import dev.yassiraitelghari.utils.RegisterValidationMessages;

import java.util.List;
import java.util.Optional;

import org.mindrot.jbcrypt.BCrypt;

public class UserServiceImp implements UserService {
    private UserRepository userRepository;

    public UserServiceImp(UserRepository userRepository) {
       this.userRepository = userRepository;
    }

    public UserServiceImp(){
        this.userRepository  = new UserRepositoryImp();
    }

    @Override
    public RegisterValidationMessages add(String username, String email, String name, String lastName, String password, String role) {
        RegisterValidationMessages validationMessages = new RegisterValidationMessages();
        validationMessages.validateAll(username, email, name, lastName, password, role);
        if (validationMessages.getEmailValidationMessage() != null && validationMessages.getEmailValidationMessage().equals("Email already exist")) {
            throw new EmailAlreadyExistsException(email);
        }
        if (validationMessages.getUsernameValidationMessage() != null && validationMessages.getUsernameValidationMessage().equals("Username already exist")) {
            throw new UsernameAlreadyExistsException(username);
        }
        if (validationMessages.getEmailValidationMessage() != null && validationMessages.getEmailValidationMessage().equals("Email format is incorrect")) {
            throw new InvalidEmailFormatException(email);
        }
        if (validationMessages.getFirstNameValidationMessage() != null && validationMessages.getLastNameValidationMessage() != null && validationMessages.getPasswordValidationMessage() != null && validationMessages.getRoleValidationMessage() != null) {
            throw new InvalidInputWhileRegisteringException();
        }


        if (validationMessages.allNull()) {
            //all null means that there is no errors where validating the user input
            User user = new User();
            user.setUsername(username);
            user.setName(name);
            user.setPassword(BCrypt.hashpw(password, BCrypt.gensalt()));
            user.setEmail(email);
            user.setRole(role);
            user.setLastName(lastName);
            user.setReplaceJeton(2);
            user.setDeleteJeton(1);
            User neUser = userRepository.add(user);
            if (neUser != null) {
                validationMessages.setRegistredUser(neUser);
            }
        } else {

        }
        return validationMessages;
    }

    @Override
    public Optional<User> get(String email, String password) {

        Optional<User> userFound = userRepository.get(email);
        if (userFound.isPresent()) {
            String hashedPassword = userFound.get().getPassword();
            boolean samePassword = BCrypt.checkpw(password, hashedPassword);
            if (samePassword) {
                return userFound;
            } else {
                throw new InvalidCredentialsException();
            }
        } else {
            throw new InvalidCredentialsException();
        }

    }

    @Override
    public ProfileUpdateValidation updateProfile(User user, String firstName, String lastName, String password, String confirmPassword) {
        ProfileUpdateValidation profileUpdateValidation = new ProfileUpdateValidation();
        profileUpdateValidation.setAll(firstName, lastName, password, confirmPassword);
        if (profileUpdateValidation.allNull()) {
            user.setName(firstName);
            user.setLastName(lastName);
            if (!password.isEmpty()) {
                user.setPassword(BCrypt.hashpw(password, BCrypt.gensalt()));
            }
            profileUpdateValidation.setUpdatedUser(userRepository.update(user));
        }
        return profileUpdateValidation;
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

    @Override
    public Optional<User> getByEmail(String email) {
        return userRepository.get(email);
    }
}