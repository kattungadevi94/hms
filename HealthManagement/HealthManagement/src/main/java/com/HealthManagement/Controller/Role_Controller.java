package com.HealthManagement.Controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/admin")
public class Role_Controller {

	@GetMapping("/greeting")
	public String greet() {
		return "Hai This Is Admin";
	}
}
