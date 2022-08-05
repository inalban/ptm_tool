package com.project.management.repositories;

import com.project.management.model.User;
import org.springframework.stereotype.Repository;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

@Repository
public interface UserRepository extends CrudRepository<User, Long> {

    User findByUsername(String username);
    //List<User> findByUserName(String username);
    User getById(Long id);


}