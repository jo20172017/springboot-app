package com.springboot.yoeli.resource;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import javax.validation.Valid;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.NotFoundException;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import com.springboot.yoeli.model.User;
import com.springboot.yoeli.service.UserService;

@Validated
@Component
@Path("api/v1/users")
public class UserResourceReastEasy {
	
    private UserService userService;
    
	@Autowired
	public UserResourceReastEasy(UserService userService) {
		this.userService = userService;
		
	}
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<User> fetchUsers(@QueryParam("gender") String gender){

		return userService.getAllUsers(Optional.ofNullable(gender));
	}
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("{userUid}")
	public User fetchUser(@PathParam("userUid") UUID userUid){
		return  userService.getUser(userUid).orElseThrow(() -> new NotFoundException("user" + userUid + "not found"));
		
		
	}
	
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public void insertNewUser(@Valid User user) {
	  userService.insertUser(user);
		
	}
	
	@PUT
	@Consumes
	@Produces
	public void updateUser(@RequestBody User user) {
		 userService.updateUser(user);
		
		
	}
	
	@DELETE
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	@Path("{userUid}")
	public void deleteUser(@PathParam("userUid")UUID userUid){
		 userService.removeUser(userUid);
		
		
	} 

}
