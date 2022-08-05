package com.project.management.services;

import com.project.management.exceptions.UsernameAlreadyExistsException;
import com.project.management.model.User;
import com.project.management.repositories.UserRepository;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;
    @Autowired
    private SessionFactory sessionFactory;



    public User saveUser(User newUser)  {

        try {
            newUser.setPassword(bCryptPasswordEncoder.encode(newUser.getPassword()));

            //Username has to be unique(exception)
            newUser.setUsername(newUser.getUsername());
            System.out.println("Get username "+newUser.getUsername());
            //Make sure that password and confirm password match
            //We don't persist or show the confirmPassword

            newUser.setConfirmPassword("");

            return userRepository.save(newUser);
        } catch (Exception e) {
            throw new UsernameAlreadyExistsException("Username'" + newUser.getUsername() + "'already exists");
        }

    }


}