package com.stephen.projectmanager.models;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.PrePersist;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name="tasks")
public class Task {
	
	@Id
	@GeneratedValue(strategy= GenerationType.IDENTITY)
	private Long id;
	
	@NotBlank(message= "Task must not be blank!")
	@Size(min=3, message="Tasks should be more than 3 characters.")
	private String text;
	
	@Column(updatable = false)
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date createdAt;
	
	@PrePersist
	protected void onCreate() {
		this.createdAt = new Date();
	}
	
	@ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="user_id")
	private User creator;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="project_id")
	private Project project;
	
	public Task() {}
	
	public Task(String text) {
		this.text = text;
	}

	public Long getId() {
		return id;
	}

	public String getText() {
		return text;
	}

	public Date getCreatedAt() {
		return createdAt;
	}

	public User getCreator() {
		return creator;
	}

	public Project getProject() {
		return project;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public void setText(String text) {
		this.text = text;
	}

	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}

	public void setCreator(User creator) {
		this.creator = creator;
	}

	public void setProject(Project project) {
		this.project = project;
	}
	
	
	
}
