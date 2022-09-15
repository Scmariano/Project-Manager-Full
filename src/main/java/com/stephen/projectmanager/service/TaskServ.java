package com.stephen.projectmanager.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.stephen.projectmanager.models.Task;
import com.stephen.projectmanager.repositories.TaskRepo;

@Service
public class TaskServ {
	@Autowired TaskRepo taskRepo;
	
	public List<Task>allTask(){
		return taskRepo.findAll();
	}
	
	public List<Task>findProjectTaskId(Long projectId){
		return taskRepo.findByProjectId(projectId);
	}
	
	public Task createTask(Task task) {
		return taskRepo.save(task);
	}
	
	public void deleteTask(Task task) {
		taskRepo.delete(task);
	}
}
