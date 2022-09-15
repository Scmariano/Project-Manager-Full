package com.stephen.projectmanager.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.stephen.projectmanager.models.Project;
import com.stephen.projectmanager.models.User;
import com.stephen.projectmanager.repositories.ProjectRepo;

@Service
public class ProjectServ {
	
	@Autowired ProjectRepo projectRepo;
	
	public List<Project> allProjects(){
		return projectRepo.findAll();
	}
	
	
	public Project createProject(Project project) {
		return projectRepo.save(project);
	}
	
	
	public Project findProjectId(Long id) {
		Optional<Project> optProject = projectRepo.findById(id);
		if(optProject.isPresent()) {
			return optProject.get();
		}else {
			return null;
		}
	}
	
	public Project updateProject(Project project) {
		return projectRepo.save(project);
	}
	
	public void deleteProject(Long id) {
		projectRepo.deleteById(id);
	}
	
	public List<Project>getJoinedUser(User user){
		return projectRepo.findAllByUsers(user);
	}
	
	public List<Project>getUnjoinedUser(User user){
		return projectRepo.findByUsersNotContains(user);
	}
	
}
