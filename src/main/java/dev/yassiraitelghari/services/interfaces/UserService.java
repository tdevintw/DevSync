package dev.yassiraitelghari.services.interfaces;

import dev.yassiraitelghari.domain.User;
import dev.yassiraitelghari.utils.ProfileUpdateValidation;
import dev.yassiraitelghari.utils.RegisterValidationMessages;

import java.util.List;
import java.util.Optional;

public interface UserService {
    RegisterValidationMessages add(String username , String email , String name , String lastName , String password , String role);

    Optional<User> get(String email , String password);

    User update(User user);

    ProfileUpdateValidation updateProfile(User user , String  firstName , String lastName , String password , String confirmPassword);


    boolean delete(User user);

    List<User> getAll();

    User findById(int id) ;

    boolean updateReplaceToken();

    boolean updateDeleteToken();

    double successPercentage(User user);

    Optional<User> getByUsername(String username);

    Optional<User> getByEmail(String email);
}