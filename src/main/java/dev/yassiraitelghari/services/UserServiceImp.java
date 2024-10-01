package dev.yassiraitelghari.services;

import dev.yassiraitelghari.domain.Role;
import dev.yassiraitelghari.domain.User;
import dev.yassiraitelghari.repositories.UserRepository;
import dev.yassiraitelghari.repositories.UserRepositoryImp;

public class UserServiceImp implements UserService{
    private UserRepository userRepository = new UserRepositoryImp();
    @Override
    public User add(User user){
        return userRepository.add(user);
    }

}
