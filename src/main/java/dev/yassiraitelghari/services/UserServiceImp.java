package dev.yassiraitelghari.services;

import dev.yassiraitelghari.domain.User;
import dev.yassiraitelghari.repositories.UserRepository;
import dev.yassiraitelghari.repositories.UserRepositoryImp;

import java.util.List;

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

    @Override
    public List<User> getAll(){
       return userRepository.getAll();
    }

    @Override
    public User findById(int id){
        return userRepository.findById(id);
    }
    @Override
    public boolean updateReplaceToken(){
        return userRepository.updateReplaceToken();
    }

    @Override
    public boolean updateDeleteToken(){
        return userRepository.updateDeleteToken();
    }

}