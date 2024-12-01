package com.HealthManagement.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.HealthManagement.Packages.User;
import com.HealthManagement.Service.UserService;

@RestController
public class UserController {
	
	@Autowired
	private UserService Service;
	
	@PostMapping("/Register")
	public User Registration(@RequestBody User user) {
		System.out.println(user);
		return Service.register(user);
	}
	
	@GetMapping("/api/user/Users")
	public List<User> getAllUsers(){
		return Service.gettUsers();
	}
	
	@PostMapping("/login")
    public String login(@RequestBody User user) {

        return Service.verify(user);
    }

}
