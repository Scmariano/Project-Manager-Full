package com.stephen.projectmanager.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.stephen.projectmanager.models.Project;
import com.stephen.projectmanager.models.User;

@Repository
public interface UserRepo extends CrudRepository<User, Long> {
	List <User> findAll();
	Optional<User> findByEmail (String email);
	User findByIdIs(Long id);
	List<User>findAllByProjects(Project project);
	List<User>findAllByProjectsNotContains(Project project);
}
