package com.oganic.oganic.user;

import org.springframework.beans.factory.annotation.Autowired;
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

import com.oganic.oganic.authenticate.JwtUtil;
import com.oganic.oganic.exception.ApiResponse;
import com.oganic.oganic.exception.ErrorResponse;

import jakarta.validation.Valid;

@RestController
@RequestMapping("api/users")
public class UserController {
    
    private final UserService userService;

	@Autowired
	private JwtUtil jwtUtil;

    public UserController(UserService userService) {
        this.userService = userService;
    }
    
	@PostMapping("/login")
	public ResponseEntity<ApiResponse> login(@RequestBody User request) {
		User user = userService.findByEmail(request.getEmail());
		PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
		if (user !=null && passwordEncoder.matches(request.getPassword(), user.getPassword())) {
			String token = jwtUtil.generateToken(user.getEmail());
			UserResponse userResponse = new UserResponse();
			userResponse.setEmail(user.getEmail());
			userResponse.setId(user.getId());
			userResponse.setToken(token);
			return ResponseEntity.ok(new ApiResponse<>(true, "Logic success", userResponse));
		} else {
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(new ApiResponse<>(false, "Invalid email or password", null));
		}
	}

    @PostMapping("/register")
	public ResponseEntity<ApiResponse> createUser(@Valid @RequestBody RegisterRequest newUser) {
		PasswordEncoder passwordEncoder = new BCryptPasswordEncoder(10);
		newUser.setPassword(passwordEncoder.encode(newUser.getPassword()));
        User user = userService.saveUser(newUser);
		return ResponseEntity.status(HttpStatus.CREATED).body(new ApiResponse<>(true, "Register success", user));
	}
}
