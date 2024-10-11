package dev.yassiraitelghari.services;

import dev.yassiraitelghari.domain.User;

import java.util.List;

public interface UserService {
    User add(User user);

    User get(String email);

    User update(User user);

    boolean delete(User user);

    List<User> getAll();

    User findById(int id) ;

    boolean updateReplaceToken();

    boolean updateDeleteToken();



}