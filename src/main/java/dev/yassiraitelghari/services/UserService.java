package dev.yassiraitelghari.services;

import dev.yassiraitelghari.domain.User;

public interface UserService {
    User add(User user);

    User get(String email);

}