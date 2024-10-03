package dev.yassiraitelghari.repositories;

import dev.yassiraitelghari.domain.User;

import java.util.List;

public interface UserRepository {
    User add(User user);

    User get(String email);

    User update(User user);

    boolean delete(User user);

    List<User> getAll();
}