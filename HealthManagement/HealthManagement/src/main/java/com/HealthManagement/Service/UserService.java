package com.HealthManagement.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.HealthManagement.Packages.Role;
import com.HealthManagement.Packages.User;
import com.HealthManagement.Repository.UserRepo;

@Service
public class UserService {
	
	@Autowired
	private UserRepo repo;

	@Autowired
	private PasswordEncoder pEncoder;
	
	  @Autowired
	    private JWTService jwtService;

	    @Autowired
	    AuthenticationManager authManager;
	
	public User register(User user) {
		
		user.setPassword(pEncoder.encode(user.getPassword()));
//		Role role = new Role();
		Role role1 = new Role();
//		role.setName("ROLE_USER");
		role1.setName("ROLE_ADMIN");
//		user.getRoles().add(role);
		user.getRoles().add(role1);
		return repo.save(user);
	}

	public List<User> gettUsers() {
		
		return repo.findAll();
	}

	public String verify(User user) {
		 Authentication authentication = authManager.authenticate(new UsernamePasswordAuthenticationToken(user.getEmail(), user.getPassword()));
		   if (authentication.isAuthenticated()) {
		         return jwtService.generateToken(user.getEmail())  ;
		        } else {
		            return "fail";
		        }
	}
	
	

}
