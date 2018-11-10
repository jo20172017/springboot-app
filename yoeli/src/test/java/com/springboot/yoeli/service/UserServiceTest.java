package com.springboot.yoeli.service;



import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.BDDMockito.given;


import java.util.List;
import java.util.Optional;
import java.util.UUID;


import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import static org.mockito.Mockito.*;
import org.mockito.MockitoAnnotations; 


import com.google.common.collect.ImmutableList;
import com.springboot.yoeli.dao.FakeDataDao;
import com.springboot.yoeli.model.User;
import com.springboot.yoeli.model.User.Gender;

public class UserServiceTest {
   @Mock
	private  FakeDataDao fakeDataDao;
	
	private  UserService userService;

	@Before
	public  void setUp() throws Exception {
		MockitoAnnotations.initMocks( this);
		userService = new UserService (fakeDataDao);
	}

	

	@Test
	public void testShouldGetAllUsers() throws Exception {
		
		UUID annaUserUid = UUID.randomUUID();
		User anna = new User(annaUserUid, "anna", "montana",Gender.FEMALE, 30, "anna@gmail.com");
		
		ImmutableList<User> users = new  ImmutableList.Builder<User>()
				.add(anna)
				.build();
		
		given(fakeDataDao.selectAllUsers()).willReturn(users);
		List <User> allUsers=userService.getAllUsers(Optional.empty());
		User user =allUsers.get(0);
		assertThat(allUsers).hasSize(1);
		assertThat(user.getAge()).isEqualTo(30);
		assertThat(user.getFirstName()).isEqualTo("anna");
		assertThat(user.getLastName()).isEqualTo("montana");
		assertThat(user.getEmail()).isEqualTo("anna@gmail.com");
		assertThat(user.getGender()).isEqualTo(Gender.FEMALE);
		assertThat(user.getUserUid()).isNotNull();
		
	}
	
	@Test
	public void shouldGetAllUserByGender() throws Exception {
		UUID annaUserUid = UUID.randomUUID();
		User anna = new User(annaUserUid, "anna", "montana",Gender.FEMALE, 30, "anna@gmail.com");
		
		UUID joeUserUid = UUID.randomUUID();
		User joe = new User(joeUserUid, "Joe", "Oyuchi",Gender.MALE, 28, "joe@gmail.com");
		
		ImmutableList<User> users = new  ImmutableList.Builder<User>()
				.add(anna)
				.add(joe)
				.build();
		
		given(fakeDataDao.selectAllUsers()).willReturn(users);
		List<User> filteredUsers = userService.getAllUsers(Optional.of("female"));
		User user = filteredUsers.get(0);
		assertThat(filteredUsers).hasSize(1);
		assertThat(user.getAge()).isEqualTo(30);
		assertThat(user.getFirstName()).isEqualTo("anna");
		assertThat(user.getLastName()).isEqualTo("montana");
		assertThat(user.getEmail()).isEqualTo("anna@gmail.com");
		assertThat(user.getGender()).isEqualTo(Gender.FEMALE);
		assertThat(user.getUserUid()).isNotNull();
		
		
	}
	@Test
	public void shouldThowExceptionWhenGenderIsInvalid() throws Exception {
		assertThatThrownBy(() -> userService.getAllUsers(Optional.of("themaaabs")))
		.isInstanceOf(IllegalStateException.class)
		.hasMessageContaining("Invalid Gender");
	}

	@Test
	public void testshouldGetUser() throws Exception {
		UUID annaUid = UUID.randomUUID();
		User anna = new User(annaUid, "anna", "montana",Gender.FEMALE, 30, "anna@gmail.com");
		
		given(fakeDataDao.selectUserByUserUid(annaUid)).willReturn(Optional.of(anna));
		
		Optional<User> optionalUser =userService.getUser(annaUid);
		
		assertThat(optionalUser.isPresent()).isTrue();
		User user = optionalUser.get();
		
		assertThat(user.getAge()).isEqualTo(30);
		assertThat(user.getFirstName()).isEqualTo("anna");
		assertThat(user.getLastName()).isEqualTo("montana");
		assertThat(user.getEmail()).isEqualTo("anna@gmail.com");
		assertThat(user.getGender()).isEqualTo(Gender.FEMALE);
		assertThat(user.getUserUid()).isNotNull();
		
		
	}

	@Test
	public void testShouldUpdateUser() throws Exception{
		
		UUID annaUid = UUID.randomUUID();
		User anna = new User(annaUid, "anna", "montana", Gender.FEMALE, 30, "anna@gmail.com");
		
		given(fakeDataDao.selectUserByUserUid(annaUid)).willReturn(Optional.of(anna));
		given(fakeDataDao.updateUser(anna)).willReturn(1);
		
		ArgumentCaptor <User> captor = ArgumentCaptor.forClass(User.class);
		
		int updateResult = userService.updateUser(anna);
		
		verify(fakeDataDao).selectUserByUserUid(annaUid);
		verify(fakeDataDao).updateUser(captor.capture());
		
		User user = captor.getValue();
		assertThat(user.getAge()).isEqualTo(30);
		assertThat(user.getFirstName()).isEqualTo("anna");
		assertThat(user.getLastName()).isEqualTo("montana");
		assertThat(user.getEmail()).isEqualTo("anna@gmail.com");
		assertThat(user.getGender()).isEqualTo(Gender.FEMALE);
		assertThat(user.getUserUid()).isNotNull();
		assertThat(updateResult).isEqualTo(1);
		
		
		
	}

	@Test
	public void testShouldRemoveUser() throws Exception{
		UUID annaUid = UUID.randomUUID();
		User anna = new User(annaUid, "anna","montana",Gender.FEMALE,30, "anna@gmail.com");
		
		given(fakeDataDao.selectUserByUserUid(annaUid)).willReturn(Optional.of(anna));
		given(fakeDataDao.deleteUserByUserUid(annaUid)).willReturn(1);
		
		ArgumentCaptor <UUID> captor = ArgumentCaptor.forClass(UUID.class);
		
		int deleteResult = userService.removeUser(annaUid);
		
		verify(fakeDataDao).selectUserByUserUid(annaUid);
		verify(fakeDataDao).deleteUserByUserUid(captor.capture());
		
	
		assertThat(deleteResult).isEqualTo(1);
		
	
	}

	@Test
	public void testShouldInsertUser() throws Exception {
		UUID annaUid = UUID.randomUUID();
		User anna = new User(annaUid, "anna","montana", Gender.FEMALE, 30, "anna@gmail.com");
		
		given(fakeDataDao.insertUser(any(UUID.class), eq(anna))).willReturn(1);
		
		ArgumentCaptor<User> captor = ArgumentCaptor.forClass(User.class);
		
		int insertResult = userService.insertUser(anna);
		
		verify(fakeDataDao).insertUser(any(UUID.class), captor.capture());
		
		User user = captor.getValue();
		
		assertThat(user.getAge()).isEqualTo(30);
		assertThat(user.getFirstName()).isEqualTo("anna");
		assertThat(user.getLastName()).isEqualTo("montana");
		assertThat(user.getEmail()).isEqualTo("anna@gmail.com");
		assertThat(user.getGender()).isEqualTo(Gender.FEMALE);
		assertThat(user.getUserUid()).isNotNull();
		assertThat(user.getUserUid()).isInstanceOf(UUID.class);
		
		assertThat(insertResult).isEqualTo(0);
		
		
	} 

}
