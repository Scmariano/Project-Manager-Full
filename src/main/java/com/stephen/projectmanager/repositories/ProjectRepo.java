package com.stephen.projectmanager.repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.stephen.projectmanager.models.Project;
import com.stephen.projectmanager.models.User;

@Repository
public interface ProjectRepo extends CrudRepository<Project, Long> {
	
	List<Project>findAll();
	List<Project>findAllByUsers(User user);
	List<Project>findByUsersNotContains(User user);
	Project findByIdIs(Long Id);
}
