package dev.yassiraitelghari.services.implmentations;

import dev.yassiraitelghari.services.interfaces.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import dev.yassiraitelghari.exceptions.*;

import static org.junit.jupiter.api.Assertions.*;

class UserServiceImpTest {

    private UserService userService;

    @BeforeEach
    public void setUp() {
        userService = new UserServiceImp();
    }

    @Test
    public void createUser_duplicateEmail_throwsEmailAlreadyExistsException() {
        String email = "client@gmail.com";
        EmailAlreadyExistsException exception = assertThrows(EmailAlreadyExistsException.class, () -> userService.add("unique", email, "yasser", "ait el ", "passsssssssss", "client"));
        assertEquals(exception.getMessage(), "Email " + email + " Already exist");
    }

    @Test
    public void createUser_duplicateUsername_throwsUsernameAlreadyExistsException() {
        String username = "client";
        UsernameAlreadyExistsException exception = assertThrows(UsernameAlreadyExistsException.class, () -> userService.add(username, "unique@gmail.com", "yasser", "ait el ", "passsssssssss", "client"));
        assertEquals(exception.getMessage(), "Username " + username + " already exists");
    }

    @Test
    public void createUser_invalidEmailFormat_throwsInvalidEmailFormatException() {
        String email = "email";
        InvalidEmailFormatException exception = assertThrows(InvalidEmailFormatException.class, () -> userService.add("unique", email, "yasser", "ait el ", "passsssssssss", "client"));
        assertEquals(exception.getMessage(), "Email " + email + " doesn't follow a valid format");
    }

    @Test
    public void createUser_emptyInputs_throwsInvalidInputWhileRegisteringException() {
        InvalidInputWhileRegisteringException exception = assertThrows(InvalidInputWhileRegisteringException.class, () -> userService.add("unique", "unique@gmail.com", "", "", "", ""));
        assertEquals(exception.getMessage(), "First name , last name , role and password Are Required");
    }
}