package com.springboot.yoeli;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.util.List;
import java.util.UUID;

import javax.ws.rs.NotFoundException;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.test.context.junit4.SpringRunner;

import com.springboot.yoeli.clientProxy.UserResourceV1;
import com.springboot.yoeli.model.User;
import com.springboot.yoeli.model.User.Gender;



//@RunWith(SpringRunner.class)
//@SpringBootTest(webEnvironment = WebEnvironment.DEFINED_PORT)
//public class YoeliApplicationTests {
	
   // @Autowired
	//private UserResourceV1 userResourceV1;
   
   
	/**
	 @Test
	public void testItShouldFetchAllUsers() {
		List<User>users =userResourceV1.fetchUsers(null);
		assertThat(users).hasSize(1);
		
		User joe = new User( null, "Joel","Oyuchi", Gender.MALE, 22, "oyuchijoel@gmail.com");
		
		assertThat(users.get(0)).isEqualToIgnoringGivenFields(joe, "userUid");
		assertThat(users.get(0).getUserUid()).isInstanceOf(UUID.class);
		assertThat(users.get(0).getUserUid()).isNotNull();
	} 
	@Test
	public void testshouldInsertUser() throws Exception {
		// Given
		UUID userUid = UUID.randomUUID();
		
		User user = new User(userUid, "Joel","Oyuchi", Gender.MALE, 22, "oyuchijoel@gmail.com");
		//When
		userResourceV1.insertNewUser(user);
		
		//Then
		User joe =userResourceV1.fetchUser(userUid);
		assertThat(joe).isEqualToComparingFieldByField(user);
		
		
	}
	@Test
	public void shouldDeleteUser() throws Exception {
		
		// Given
				UUID userUid = UUID.randomUUID();
				
				User user = new User(userUid, "Joel","Oyuchi", Gender.MALE, 22, "oyuchijoel@gmail.com");
				//When
				userResourceV1.insertNewUser(user);
				
				//Then
				User joe =userResourceV1.fetchUser(userUid);
				assertThat(joe).isEqualToComparingFieldByField(user);
				
				//WHen
				userResourceV1.deleteUser(userUid);
				
				//Then
				assertThatThrownBy(() -> userResourceV1.fetchUser(userUid)).isInstanceOf(NotFoundException.class);
		
	}
	@Test
	public void shouldUpdateUser () throws Exception {
		
		// Given
				UUID userUid = UUID.randomUUID();
				
				User user = new User(userUid, "Joel","Oyuchi", Gender.MALE, 22, "oyuchijoel@gmail.com");
				//When
				userResourceV1.insertNewUser(user);
				User updatedUser = new User(userUid, "Alex","Oyuchi", Gender.MALE, 55, "oyuchialex@gmail.com");
				userResourceV1.updateUser(updatedUser);
				
				//Then
				user =userResourceV1.fetchUser(userUid);
				assertThat(user).isEqualToComparingFieldByField(updatedUser);
	}
	

}**/
