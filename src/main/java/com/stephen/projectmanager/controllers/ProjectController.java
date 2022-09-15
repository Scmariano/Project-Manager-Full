package com.stephen.projectmanager.controllers;


import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.stephen.projectmanager.models.Project;
import com.stephen.projectmanager.models.Task;
import com.stephen.projectmanager.models.User;
import com.stephen.projectmanager.service.ProjectServ;
import com.stephen.projectmanager.service.TaskServ;
import com.stephen.projectmanager.service.UserServ;

@Controller
public class ProjectController {
	@Autowired ProjectServ projectServ;
	@Autowired UserServ userServ;
	@Autowired TaskServ taskServ;
	
	
	@GetMapping("/dashboard")
	public String dashboard(HttpSession session, Model model) {
		Long userId = (Long) session.getAttribute("userId");
		if(userId == null) {
			return "redirect:/logout";
		}else {
			model.addAttribute("user", userServ.findById(userId));
			model.addAttribute("projects", projectServ.allProjects());
			model.addAttribute("assignedProjects", projectServ.getJoinedUser(userServ.findById(userId)));
			model.addAttribute("unAssignedProjects", projectServ.getUnjoinedUser(userServ.findById(userId)));
			return "dashboard.jsp";
		}
	}
	
	@GetMapping("/projects/{projectId}")
	public String showProject(@PathVariable("projectId")Long id, HttpSession session, Model model) {
		Long userId = (Long) session.getAttribute("userId");
		if(userId == null) {
			return "redirect:/logout";
		} else {
			model.addAttribute("user", userServ.findById(userId));
			model.addAttribute("project", projectServ.findProjectId(id));
			return "showProject.jsp";
		}
	}
	
	@RequestMapping("/projects/{id}/delete")
	public String destroy(@PathVariable("id")Long id, HttpSession session) {
		Long userId = (Long) session.getAttribute("userId");
		if(userId == null) {
			return "redirect:/logout";
		}
		
		for(Task text:taskServ.findProjectTaskId(id)) {
			taskServ.deleteTask(text);
		}
		projectServ.deleteProject(id);
		return "redirect:/dashboard";
	}
	
	
	@GetMapping("/dashboard/{projectId}/join")
	public String join(@PathVariable("projectId")Long projectId,HttpSession session, Model model) {
		Long userId = (Long) session.getAttribute("userId");
		Project project = projectServ.findProjectId(projectId);
		User user = userServ.findById(userId);
		if(userId == null) {
			return "redirect:/logout";
		}else {
			user.getProjects().add(project);
			userServ.updateUser(user);
			model.addAttribute("user", user);
			model.addAttribute("unAssignedProjects", projectServ.getUnjoinedUser(user));
			model.addAttribute("assignedProjects", projectServ.getJoinedUser(user));
			return "redirect:/dashboard";
		}
	}
	
	@GetMapping("/dashboard/{projectId}/leave")
	public String leave(@PathVariable("projectId")Long projectId,HttpSession session, Model model) {
		Long userId = (Long) session.getAttribute("userId");
		Project project = projectServ.findProjectId(projectId);
		User user = userServ.findById(userId);
		if(userId == null) {
			return "redirect:/logout";
		}else {
			user.getProjects().remove(project);
			userServ.updateUser(user);
			model.addAttribute("user", user);
			model.addAttribute("unAssignedProjects", projectServ.getUnjoinedUser(user));
			model.addAttribute("assignedProjects", projectServ.getJoinedUser(user));
			return "redirect:/dashboard";
		}
	}
	
	
	@GetMapping("/projects/{projectId}/edit")
	public String edit(@PathVariable("projectId")Long projectId, Model model, HttpSession session) {
		Long userId = (Long) session.getAttribute("userId");
		Project project = projectServ.findProjectId(projectId);
		if(userId==null) {
			return "redirect:/logout";
		}else {
			model.addAttribute("project", project);
			return "editProject.jsp";
		}
	}
	
	@PutMapping("/projects/{id}/edit")
	public String updateProject(@Valid @ModelAttribute("project") Project project,
			BindingResult result, HttpSession session, @PathVariable("id") Long id) {
		Long userId = (Long) session.getAttribute("userId");
		Project thisProject = projectServ.findProjectId(id);
		if(userId == null) {
			return "redirect:/logout";
		}
		
		if (result.hasErrors()) {
    		return "editProject.jsp";
    	}else {
    		project.setLead(thisProject.getLead());
    		project.setUsers(thisProject.getUsers());
    		projectServ.updateProject(project);
    		return "redirect:/dashboard";
    	}
	}
	
	
	@GetMapping("/projects/new")
	public String newProject(@ModelAttribute("project") Project project, HttpSession session) {
		Long userId = (Long) session.getAttribute("userId");
		if(userId == null) {
			return "redirect:/logout";
		}else {
			return "newProject.jsp";
		}
	}
	
	
	@PostMapping("/projects/create")
	public String createProject(@Valid @ModelAttribute("project") Project project, BindingResult result,
			HttpSession session) {
		Long userId = (Long) session.getAttribute("userId");
		User loggedUser = userServ.findById(userId);
		if(userId == null) {
			return "redirect:/logout";
		}else {
			if(result.hasErrors()) {
				return "newProject.jsp";
			}else {
				Project newProject = new Project(project.getTitle(), project.getDescription(),
						project.getDueDate(), project.getLead(), project.getTasks());
				newProject.setLead(loggedUser);
				projectServ.createProject(newProject);
				loggedUser.getProjects().add(newProject);
				userServ.updateUser(loggedUser);
				return "redirect:/dashboard";
			}
		}
	}
	
	@GetMapping("/projects/{id}/tasks")
	public String task(@PathVariable("id") Long id, @ModelAttribute("task") Task task, Model model,
			HttpSession session) {
		Long userId = (Long) session.getAttribute("userId");
		Project thisProject = projectServ.findProjectId(id);
		User loggedUser = userServ.findById(userId);
		if(userId==null) {
			return "redirect:/logout";
		}else {
			model.addAttribute("user", loggedUser);
			model.addAttribute("project", thisProject);
		
			return "task.jsp";
		}
	}
	@PostMapping("/projects/{id}/tasks")
	public String newProjectTask(@Valid @ModelAttribute("task") Task task, BindingResult result, 
			 @PathVariable("id") Long id, HttpSession session, Model model) {
		
		if(session.getAttribute("userId") == null) {
			return "redirect:/logout";
		}
		Long userId = (Long) session.getAttribute("userId");
		
		Project project = projectServ.findProjectId(id);
		
		if(result.hasErrors()) {
			model.addAttribute("project", project);
			model.addAttribute("tasks", taskServ.findProjectTaskId(id));
			return "task.jsp";
		}else {
			Task newTask = new Task(task.getText());
			newTask.setProject(project);
			newTask.setCreator(userServ.findById(userId));
			taskServ.createTask(newTask);
			return "redirect:/projects/{id}/tasks";
		}
	}
	
	
}
