package com.springboot.yoeli.dao;



import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.junit.BeforeClass;
import org.junit.Test;

import com.springboot.yoeli.model.User;
import com.springboot.yoeli.model.User.Gender;

public class FakeDataDaoTest {
	
	private static  FakeDataDao fakeDataDao;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		fakeDataDao = new FakeDataDao();
	}

	@Test 
	public void testShouldSelectAllUsers() throws Exception {
		List <User> users = fakeDataDao.selectAllUsers();
		assertThat(users).hasSize(1);
		User user = users.get(0);
		assertThat(user.getAge()).isEqualTo(22);
		assertThat(user.getFirstName()).isEqualTo("Joel");
		assertThat(user.getLastName()).isEqualTo("Oyuchi");
		assertThat(user.getEmail()).isEqualTo("oyuchijoel@gmail.com");
		assertThat(user.getGender()).isEqualTo(Gender.MALE);
		assertThat(user.getUserUid()).isNotNull();
		
	
	}

	@Test
	public void testShouldSelectUserByUserUid() throws Exception {
		UUID annaUserUid = UUID.randomUUID();
		User anna = new User(annaUserUid, "anna", "Joe", Gender.FEMALE, 30, "annajoe@gmail.com");
		fakeDataDao.insertUser(annaUserUid, anna);
		
		assertThat(fakeDataDao.selectAllUsers()).hasSize(2);
		Optional<User> annaOptional = fakeDataDao.selectUserByUserUid(annaUserUid);
		
		assertThat(annaOptional.isPresent()).isTrue();
		assertThat(annaOptional.get()).isEqualTo(anna);
		
		
		
	}
	
	public void testShouldNotSelectUserByRandomId() throws Exception {
		
		Optional<User> user =fakeDataDao.selectUserByUserUid(UUID.randomUUID());
		assertThat(user.isPresent()).isFalse();
	}

	@Test
	public void testShouldUpdateUser() throws Exception {
		UUID joeUserUid = fakeDataDao.selectAllUsers().get(0).getUserUid();
		User newJoe = new User(joeUserUid, "anna", "montana", Gender.FEMALE, 30, "anna@gmail.com");
		fakeDataDao.updateUser(newJoe);
	Optional<User>	user= fakeDataDao.selectUserByUserUid(joeUserUid);
	assertThat(user.isPresent()).isTrue();
	assertThat(fakeDataDao.selectAllUsers()).hasSize(2);
	assertThat(user.get()).isEqualToComparingFieldByField(newJoe);
		
	
	}

	@Test
	public void testDeleteUserByUserUid() throws Exception {
		UUID joeUserUid = fakeDataDao.selectAllUsers().get(0).getUserUid();
		fakeDataDao.deleteUserByUserUid(joeUserUid);
		assertThat(fakeDataDao.selectUserByUserUid(joeUserUid).isPresent()).isFalse();
		assertThat(fakeDataDao.selectAllUsers().isEmpty());
	
	}

	@Test
	public void testInsertUser() throws Exception{
		UUID userUid = UUID.randomUUID();
		User user = new User(userUid, "anna", "montana",Gender.FEMALE, 30, "anna@gmail.com");
		fakeDataDao.insertUser(userUid, user);
		List <User>users= fakeDataDao.selectAllUsers();
		assertThat(users).hasSize(2);
		assertThat(fakeDataDao.selectUserByUserUid(userUid).get()).isEqualToComparingFieldByField(user);
		
	
	}

}
