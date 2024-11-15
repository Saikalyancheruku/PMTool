package com.projectmanagementbackend.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.projectmanagementbackend.Entities.Tasks;
import com.projectmanagementbackend.Repository.TaskRepository;

@Service
public class TaskService {

	
private final TaskRepository taskRepository;
	@Autowired
	public TaskService(TaskRepository taskRepository) {
		super();
		this.taskRepository = taskRepository;
	}
	public ResponseEntity<Tasks> CreateTask(Tasks task) {
		Tasks Createdtask =this.taskRepository.save(task);
		return ResponseEntity.ok(Createdtask);
	}
	
	public ResponseEntity<Tasks> getTaskById(Long id) {
		Tasks task = this.taskRepository.getReferenceById(id);
		return ResponseEntity.ok(task);
	}
	
	

	
	
	
	
}
