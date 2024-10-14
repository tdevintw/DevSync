package dev.yassiraitelghari.repositories;

import dev.yassiraitelghari.domain.User;

import java.util.List;
import java.util.Optional;

public interface UserRepository {
    User add(User user);

    Optional<User> get(String email);

    User update(User user);

    boolean delete(User user);

    List<User> getAll();

    User findById(int id);

    boolean updateReplaceToken();

    boolean updateDeleteToken();

    Optional<User> getByUsername(String username);

}