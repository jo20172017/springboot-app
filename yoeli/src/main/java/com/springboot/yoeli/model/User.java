package com.springboot.yoeli.model;

import java.time.LocalDate;
import java.util.UUID;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Email;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class User {
	
	private final UUID userUid;
	@NotNull
	private final String firstName;
	@NotNull
	private final  String lastName;
	@NotNull
	private final Gender gender;
	@NotNull
	@Max(value= 112)
	@Min(value = 0)
	private final int age;
	
	@NotNull
	@Email
	private final String email;
	
	public enum Gender {
		MALE,
		FEMALE
	}
	
	
	public User(
			@JsonProperty ("userUid")UUID userUid, 
			@JsonProperty ("firstName")String firstName,
			@JsonProperty ("lastName")String lastName,
			@JsonProperty ("gender")Gender gender,
			@JsonProperty ("age")int age, 
			@JsonProperty ("email")String email) {
		super();
		this.userUid = userUid;
		this.firstName = firstName;
		this.lastName = lastName;
		this.gender = gender;
		this.age = age;
		this.email = email;
	}
	
    public static User newUser(UUID userUid, User user) {
	     return new User(userUid, user.getFirstName(), user.getLastName(), user.gender, user.getAge(),user.getEmail());
	
}




	@Override
	public String toString() {
		return "User [userUid=" + userUid + ", firstName=" + firstName + ", lastName=" + lastName + ", gender=" + gender
				+ ", age=" + age + ", email=" + email + "]";
	}




    //@JsonProperty("id")
	public UUID getUserUid() {
		return userUid;
	}
	


	public String getFirstName() {
		return firstName;
	}


	public String getLastName() {
		return lastName;
	}


	public Gender getGender() {
		return gender;
	}


	public int getAge() {
		return age;
	}

   // @JsonIgnore
	public String getEmail() {
		return email;
	}

   // computed json property
	public String getFullNames() {
		return firstName + " "+ lastName;
	}
	//LocalDate
	public int getDateOfBirth() {
		return LocalDate.now().minusYears(age).getYear();
	}




	
	
	

}
