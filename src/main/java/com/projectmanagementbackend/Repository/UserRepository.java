package com.projectmanagementbackend.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.projectmanagementbackend.Entities.Users;
@Repository
public interface UserRepository extends JpaRepository<Users, Long>{
	Users  findByEmailAddress(String EmailAddress);
	boolean existsByEmailAddress(String EmailAddress);
}
