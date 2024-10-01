package com.Icwd.user.service.controllers;

import java.util.List;

import com.Icwd.user.service.entities.AuthenticationResponse;
import com.Icwd.user.service.exceptions.ResourceNotFoundException;
import com.Icwd.user.service.services.AuthenticationService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.Icwd.user.service.entities.User;
import com.Icwd.user.service.services.UserService;

@RestController
//@CrossOrigin(origins = "http://localhost:3000") // Allow your React app's origin
public class UserController {

	@Autowired
	private UserService userService;

	private final AuthenticationService authService;

    public UserController(AuthenticationService authService) {
        this.authService = authService;
    }


    @PostMapping("/register")
	public ResponseEntity<AuthenticationResponse> register(
			@RequestBody User request
	) {
		return ResponseEntity.ok(authService.register(request));
	}

	@PostMapping("/login")
	public ResponseEntity<AuthenticationResponse> login(
			@RequestBody User request
	) {
		return ResponseEntity.ok(authService.authenticate(request));
	}

	@PostMapping("/refresh_token")
	public ResponseEntity refreshToken(
			HttpServletRequest request,
			HttpServletResponse response
	) {
		return authService.refreshToken(request, response);
	}

	//single user get
	@GetMapping("/{userId}")
	public ResponseEntity<User> getSingleUser(@PathVariable String userId){
		User user=userService.getUserByUserId(Integer.parseInt(userId));
		return ResponseEntity.ok(user);
	}

	//get all user

	@GetMapping
	public ResponseEntity<List<User>> getAllUser(){
		List<User> allUser=userService.getAllUser();
		return ResponseEntity.ok(allUser);
	}

	// Delete user
	@DeleteMapping("/{userId}")
	public ResponseEntity<Void> deleteUser(@PathVariable String userId){
		boolean isDeleted = userService.deleteUser(Integer.parseInt(userId));
		if (isDeleted) {
			return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
		} else {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		}
	}

	@PutMapping("/{userId}")
	public ResponseEntity<User> updateUser(@PathVariable("userId") String userId, @RequestBody User userDetails) {
		try {
			// Retrieve the existing user by userId
			User existingUser = userService.getUserByUserId(Integer.parseInt(userId));

			if (existingUser == null) {
				return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
			}

			// Update the fields of the existing user with details from the request
			existingUser.setName(userDetails.getName());
			existingUser.setEmail(userDetails.getEmail());
			existingUser.setAbout(userDetails.getAbout());
			existingUser.setPassword(userDetails.getPassword());

			// Save the updated user
			User updatedUser = userService.saveUser(existingUser);

			return ResponseEntity.ok(updatedUser);
		} catch (Exception e) {

			e.printStackTrace();

			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
		}
	}


}
