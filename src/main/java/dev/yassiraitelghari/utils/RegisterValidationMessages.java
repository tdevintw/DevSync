package dev.yassiraitelghari.utils;

import java.util.Optional;

import dev.yassiraitelghari.domain.User;
import dev.yassiraitelghari.exceptions.EmailAlreadyExistsException;
import dev.yassiraitelghari.services.interfaces.UserService;
import dev.yassiraitelghari.services.implmentations.UserServiceImp;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegisterValidationMessages  {

    private UserService userService = new UserServiceImp();


    private User registredUser;

    private static final String EMAIL_PATTERN = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$";

    // Compile the pattern
    private static final Pattern pattern = Pattern.compile(EMAIL_PATTERN);

    public User getRegistredUser() {
        return registredUser;
    }

    public void setRegistredUser(User registredUser) {
        this.registredUser = registredUser;
    }

    private String usernameValidationMessage;

    private String firstNameValidationMessage;

    private String lastNameValidationMessage;

    private String emailValidationMessage;

    private String passwordValidationMessage;

    private String roleValidationMessage;

    public boolean allNull() {
        return usernameValidationMessage == null && firstNameValidationMessage == null && lastNameValidationMessage == null && emailValidationMessage == null && passwordValidationMessage == null && roleValidationMessage == null;
    }

    public void validateAll(String username, String email, String name, String lastName, String password, String role) {
        setUsernameValidationMessage(username);
        setFirstNameValidationMessage(name);
        setLastNameValidationMessage(lastName);
        setRoleValidationMessage(role);
        setPasswordValidationMessage(password);
        setEmailValidationMessage(email);
    }


    public String getUsernameValidationMessage() {
        return usernameValidationMessage;
    }

    public void setUsernameValidationMessage(String username) {
        Optional<User> findUser = userService.getByUsername(username);
        if (findUser.isEmpty()) {
            if (username.length() < 3) {
                usernameValidationMessage = "Username is too short";
            }
        } else {
            usernameValidationMessage = "Username already exist";
        }
    }

    public String getFirstNameValidationMessage() {
        return firstNameValidationMessage;
    }

    public void setFirstNameValidationMessage(String firstName) {
        if (firstName.length() < 3) {
            firstNameValidationMessage = "First Name is too short";
        }
    }

    public String getLastNameValidationMessage() {
        return lastNameValidationMessage;
    }

    public void setLastNameValidationMessage(String lastName) {
        if (lastName.length() < 3) {
            lastNameValidationMessage = "Last Name is too short";
        }
    }

    public String getEmailValidationMessage() {
        return emailValidationMessage;
    }

    public void setEmailValidationMessage(String email) {
        Optional<User> findUser = userService.getByEmail(email );
        if (findUser.isEmpty()) {
            Matcher matcher = pattern.matcher(email);
            if (!matcher.matches()) {
                emailValidationMessage = "Email format is incorrect";
            }
        } else {
            emailValidationMessage = "Email already exist";
        }
    }

    public String getPasswordValidationMessage() {
        return passwordValidationMessage;
    }

    public void setPasswordValidationMessage(String password) {
        if (password.length() < 8) {
            passwordValidationMessage = "Password must be least 8 character";
        }
    }

    public String getRoleValidationMessage() {
        return roleValidationMessage;
    }

    public void setRoleValidationMessage(String role) {
        if (!role.equals("MANAGER") &&  !role.equals("CLIENT")) {
            roleValidationMessage = "Invalid role specified.";
        }
    }


}
