package dev.yassiraitelghari.services;

import dev.yassiraitelghari.domain.User;
import dev.yassiraitelghari.utils.RegisterValidationMessages;

import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;

public interface UserService {
    RegisterValidationMessages add(String username , String email , String name , String lastName , String password , String role);

    Optional<User> get(String email);

    User update(User user);

    boolean delete(User user);

    List<User> getAll();

    User findById(int id) ;

    boolean updateReplaceToken();

    boolean updateDeleteToken();

    double successPercentage(User user);

    Optional<User> getByUsername(String username);

}