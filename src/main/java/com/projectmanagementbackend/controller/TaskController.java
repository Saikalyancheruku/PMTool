package com.projectmanagementbackend.controller;

import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.projectmanagementbackend.Entities.Tasks;
import com.projectmanagementbackend.service.TaskService;

//import io.swagger.v3.oas.annotations.parameters.RequestBody;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequestMapping("/api/tasks")
public class TaskController {
		

	private final TaskService taskService;
	
	public TaskController(TaskService taskService) {
		this.taskService=taskService;
	}

	@PostMapping("/createtask")
	public ResponseEntity<Tasks> createTask(@RequestBody Tasks Task){
		return this.taskService.CreateTask(Task);
		
	}
	@GetMapping("/gettask/{id}")
	public ResponseEntity<Tasks> getTaskById(@PathVariable Long id){
		return this.taskService.getTaskById(id);
		
	}
	
	
}
