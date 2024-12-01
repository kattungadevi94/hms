package com.HealthManagement.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.HealthManagement.Packages.Role;


@Repository
public interface RoleRepo extends JpaRepository<Role, Long>{
//	
//	Role FindByName(String name);
}
