package com.HealthManagement.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.HealthManagement.Packages.User;

@Repository
public interface UserRepo extends JpaRepository<User, Long> {
	
	User findByEmail(String email);
	
	

}


