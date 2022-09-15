package com.stephen.projectmanager.models;

import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Table;
import javax.validation.constraints.Future;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name="projects")
public class Project {
	@Id
	@GeneratedValue(strategy= GenerationType.IDENTITY)
	private Long id;
	
	@NotBlank(message= "Title must not be blank!")
	@Size(min=3, message="Title should be more than 3 characters.")
	private String title;
	
	@NotBlank(message= "description must not be blank!")
	private String description;
	
	@Future
    @DateTimeFormat(pattern="yyyy-MM-dd")
    private Date dueDate;
	
	@Column(updatable = false)
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date createdAt;
	
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date updatedAt;
	
	@ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="user_id")
    private User lead;
	
    
    @ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(
			name = "users_projects",
			joinColumns = @JoinColumn(name = "project_id"),
			inverseJoinColumns = @JoinColumn(name = "user_id")
	)
    private List<User> users;
    
    @Column(updatable=false)
    @OneToMany(mappedBy="project", fetch=FetchType.LAZY)
    private List<Task> tasks;

	public Project() {}

	public Project(String title, String description, Date dueDate, User lead, List<Task> tasks) {
		this.title = title;
		this.description = description;
		this.dueDate = dueDate;
		this.lead = lead;
		this.tasks= tasks;

	}

	public Long getId() {
		return id;
	}

	public String getTitle() {
		return title;
	}

	public String getDescription() {
		return description;
	}

	public Date getDueDate() {
		return dueDate;
	}

	public Date getCreatedAt() {
		return createdAt;
	}

	public Date getUpdatedAt() {
		return updatedAt;
	}

	public User getLead() {
		return lead;
	}


	public List<User> getUsers() {
		return users;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public void setDueDate(Date dueDate) {
		this.dueDate = dueDate;
	}

	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}

	public void setUpdatedAt(Date updatedAt) {
		this.updatedAt = updatedAt;
	}

	public void setLead(User lead) {
		this.lead = lead;
	}

	public void setUsers(List<User> users) {
		this.users = users;
	}
	

	public List<Task> getTasks() {
		return tasks;
	}

	public void setTasks(List<Task> tasks) {
		this.tasks = tasks;
	}

	@PrePersist
	protected void onCreate() {
		this.createdAt = new Date();
	}
	
	@PreUpdate
	protected void onUpdate() {
		this.updatedAt = new Date();
	}
}
