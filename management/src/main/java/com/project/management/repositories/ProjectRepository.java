package com.project.management.repositories;

import com.project.management.model.Project;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProjectRepository extends CrudRepository<Project, Long> {
    Project findByProjectIdentifier(String projectId);


    Iterable<Project> findAll();

    Iterable<Project> findAllByProjectLeader(String username);


}