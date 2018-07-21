package com.springboot.yoeli.resource;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.springboot.yoeli.model.User;
import com.springboot.yoeli.service.UserService;

//@RestController
//@RequestMapping(
//		path = "api/v1/users"
//		)

public class UserResourceMVC {
	
	private UserService userService;
	
	@Autowired
	public UserResourceMVC(UserService userService) {
		this.userService = userService;
		
	}
	//@RequestMapping(
		//	method = RequestMethod.GET,
		//	produces = MediaType.APPLICATION_JSON
	//		)
	public List<User> fetchUsers(@QueryParam("gender") String gender){

		return userService.getAllUsers(Optional.ofNullable(gender));
	}
	
	@RequestMapping( method = RequestMethod.GET,
			produces = MediaType.APPLICATION_JSON,
			path = "{userUid}")
	public ResponseEntity<?> fetchUser(@PathVariable("userUid") UUID userUid){
		Optional<User> userOptional = userService.getUser(userUid);
		
		if(userOptional.isPresent()) {
			return ResponseEntity.ok(userOptional.get());
		}
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(" user " + userUid + " was not found");
		
	}
	
	@RequestMapping(
			method = RequestMethod.POST,
			consumes = MediaType.APPLICATION_JSON,
			produces = MediaType.APPLICATION_JSON
			)
	public ResponseEntity<Integer> insertNewUser(@RequestBody User user) {
		int result = userService.insertUser(user);
		if(result == 1) {
			return ResponseEntity.ok().build();
		} 
		return ResponseEntity.badRequest().build();
	}
	
	@RequestMapping (
			method = RequestMethod.PUT,
			consumes = MediaType.APPLICATION_JSON,
			produces = MediaType.APPLICATION_JSON
			)
	public ResponseEntity<Integer> updateUser(@RequestBody User user) {
		int result = userService.updateUser(user);
		if(result == 1) {
			return ResponseEntity.ok().build();
		}
		return ResponseEntity.badRequest().build();
		
	}
	
	@RequestMapping(
			method = RequestMethod.DELETE,
			path= "{userUid}",
			produces = MediaType.APPLICATION_JSON)
	public ResponseEntity<Integer> deleteUser(@PathVariable("userUid")UUID userUid){
		int result = userService.removeUser(userUid);
		if(result == 1) {
			return ResponseEntity.ok().build();
			
		}
		return ResponseEntity.badRequest().build();
		
	} 

}
