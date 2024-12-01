package com.HealthManagement.Config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.HealthManagement.Service.UserDetailsServiceImple;

@Configuration
@EnableWebSecurity
public class UserConfig {
	
	 @Autowired
	    private JwtFilter jwtFilter;
	
	@Autowired
	 private UserDetailsServiceImple customUserDetailsService;

	    @Bean
	    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
	        return http
	            .csrf(Customizer->Customizer.disable())
	            .authorizeHttpRequests((authz) -> authz
	                .requestMatchers("/Register","/login").permitAll()
	                .requestMatchers("/api/admin/**").hasAnyRole("ADMIN")
	                .requestMatchers("/api/user/**").hasAnyRole("USER","ADMIN")
	                .anyRequest().authenticated()
	            )
	            .httpBasic(Customizer.withDefaults())
	            .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class)
                .build();

	       
	    }

	    @Bean
	    public AuthenticationProvider authenticatisetonprovider() {
	    	DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
	    	provider.setPasswordEncoder(passwordEncoder());
	    	provider.setUserDetailsService(customUserDetailsService);
	    	return provider;
	    }
	    @Bean
	    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
	        return authenticationConfiguration.getAuthenticationManager();
	    }

	    @Bean
	    public PasswordEncoder passwordEncoder() {
	        return new BCryptPasswordEncoder();
	    }
	  

}
