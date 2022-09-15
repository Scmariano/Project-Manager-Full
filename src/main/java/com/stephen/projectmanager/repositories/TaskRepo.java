package com.stephen.projectmanager.repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.stephen.projectmanager.models.Task;

@Repository
public interface TaskRepo extends CrudRepository<Task, Long> {
	
	List<Task>findAll();
	List<Task>findByProjectId(Long id);
}
