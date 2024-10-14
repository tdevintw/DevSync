package dev.yassiraitelghari.services;

import dev.yassiraitelghari.domain.User;

import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;

public interface UserService {
    User add(String username ,String email ,String name ,String lastName ,String password ,String role);

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