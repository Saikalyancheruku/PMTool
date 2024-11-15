package com.projectmanagementbackend.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.projectmanagementbackend.Entities.Tasks;

@Repository
public interface TaskRepository extends JpaRepository<Tasks, Long>{
	
	
}
