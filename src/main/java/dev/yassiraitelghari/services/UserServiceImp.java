package dev.yassiraitelghari.services;

import dev.yassiraitelghari.domain.User;
import dev.yassiraitelghari.repositories.UserRepository;
import dev.yassiraitelghari.repositories.UserRepositoryImp;

public class UserServiceImp implements UserService{
    private UserRepository userRepository = new UserRepositoryImp();
    @Override
    public User add(User user){
        return userRepository.add(user);
    }

    @Override
    public User get(String email){
        return userRepository.get(email);
    }

    @Override
    public User update(User user){
        return userRepository.update(user);
    }

    @Override
    public boolean delete(User user){
        return userRepository.delete(user);
    }

}