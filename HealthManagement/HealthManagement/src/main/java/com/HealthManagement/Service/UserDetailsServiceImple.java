package com.HealthManagement.Service;

import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.HealthManagement.Packages.User;
import com.HealthManagement.Repository.UserRepo;


@Service
public class UserDetailsServiceImple implements UserDetailsService {
	
	@Autowired
	private UserRepo userRepo;
	

	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		
		User user = userRepo.findByEmail(email);
		if(user==null) {
			throw new UsernameNotFoundException("User Not Found");
		}
		return  new org.springframework.security.core.userdetails.User(
	            user.getEmail(), 
	            user.getPassword(), 
	            user.getRoles().stream()
	                .map(role -> new SimpleGrantedAuthority(role.getName()))
	                .collect(Collectors.toList())
	        );
	}

}
