package com.oganic.oganic.user;

import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Valid;

@RestController
@RequestMapping("api/users")
public class UserController {
    
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }
    
	@PostMapping("/login")
	public ResponseEntity<Object> login(@RequestBody User request) {
		User user = userService.findByEmail(request.getEmail());
		PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
		boolean isAuthenticate = passwordEncoder.matches(request.getPassword(), user.getPassword());
		return ResponseEntity.status(HttpStatus.ACCEPTED).body(isAuthenticate);
	}

    @PostMapping("/register")
	public ResponseEntity<User> createUser(@Valid @RequestBody User newUser) {
		PasswordEncoder passwordEncoder = new BCryptPasswordEncoder(10);
		newUser.setPassword(passwordEncoder.encode(newUser.getPassword()));
        newUser = userService.saveUser(newUser);
		return ResponseEntity.status(HttpStatus.CREATED).body(newUser);
	}
}
