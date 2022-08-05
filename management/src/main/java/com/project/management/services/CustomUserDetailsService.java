package com.project.management.services;

import com.project.management.model.User;
import com.project.management.repositories.UserRepository;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    SessionFactory sessionFactory;

    @Transactional
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username);
        if (user == null) new UsernameNotFoundException("User not found ");
        return user;
    }


//    @Transactional
//    public User loadUserById(Long id) {
//        User u = sessionFactory.getCurrentSession().load(User.class, id);
//        u = userRepository.getById(id);
//        System.out.print(u);
//        return u;
//    }

        @Transactional
    public User loadUserById(Long id) {
        User user = userRepository.getById(id);
            System.out.println("user----> "+user.toString());
        if (user == null) new UsernameNotFoundException("User not found");
        return user;
    }
}