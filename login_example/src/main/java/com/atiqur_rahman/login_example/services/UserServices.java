package com.atiqur_rahman.login_example.services;

import com.atiqur_rahman.login_example.exception.UserAlreadyRegisterException;
import com.atiqur_rahman.login_example.exception.UserNotFoundException;
import com.atiqur_rahman.login_example.model.User;
import com.atiqur_rahman.login_example.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by salem on 23/10/2018.
 */
@Service
public class UserServices {

    private UserRepository userRepository;

    public UserServices(UserRepository userRepository) {
        this.userRepository = userRepository;
    }




    public User registerNewUser(String userName,String email,String password) throws UserAlreadyRegisterException {

        User user =this.userRepository.findByEmail(email);
        if(user!=null)
            throw new UserAlreadyRegisterException("this email already register");

        User registerUser=new User();
        registerUser.setName(userName);
        registerUser.setEmail(email);
        registerUser.setPassword(password);
        registerUser.setDisabled(false);
        return this.userRepository.save(registerUser);
    }

    public User findUserByEmailAndPassword(String email,String password) throws UserNotFoundException {

       User user= this.userRepository.findByEmailAndPassword(email,password);
       if(user==null)
           throw new UserNotFoundException("User not found Exception");

       return user;
    }

    public Iterable<User> findAll(){
        return this.userRepository.findAll();
    }

    public User save(User user){
        return this.userRepository.save(user);
    }
    public User findOne(Long userId){
        return this.userRepository.findOne(userId);
    }
}
