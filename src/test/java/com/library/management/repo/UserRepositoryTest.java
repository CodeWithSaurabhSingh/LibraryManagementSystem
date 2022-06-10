package com.library.management.repo;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.library.management.entity.UserEntity;
import com.library.management.repository.UserRepository;

@SpringBootTest
public class UserRepositoryTest {

	@Autowired
	private UserRepository userRepo;
	
	@Test
	void UserExistByLibraryCardNo() {
		UserEntity user=new UserEntity();
		user.setLibCardNo("abc9999999998");
		user.setUserContactNo(9999999998L);
		user.setUserEmailId("abcd@email");
		user.setUserName("abcd");
		userRepo.save(user);
		UserEntity actualResultUser =userRepo.findByLibCardNo("abc9999999998");
		assertEquals(user.getLibCardNo(), actualResultUser.getLibCardNo());
	}
	
	@Test
	void findByUserEmailId() {
		UserEntity user=new UserEntity();
		user.setLibCardNo("abc9999999997");
		user.setUserContactNo(9999999998L);
		user.setUserEmailId("abcde@email");
		user.setUserName("abcde");
		userRepo.save(user);
		UserEntity actualResultUser =userRepo.findByUserEmailId("abcde@email");
		assertEquals(user.getUserEmailId(), actualResultUser.getUserEmailId());
	}
	@Test
	void findByUserName(){
		UserEntity user=new UserEntity();
		user.setLibCardNo("abc9999999997");
		user.setUserContactNo(9999999998L);
		user.setUserEmailId("abcde@email");
		user.setUserName("abcdef");
		userRepo.save(user);
		UserEntity actualResultUser =userRepo.findByUserName("abcdef");
		assertEquals(user.getUserName(), actualResultUser.getUserName());
	}
	
	@Test
	void findByUserContactNo(){
		UserEntity user=new UserEntity();
		user.setLibCardNo("abc9999999997");
		user.setUserContactNo(9999999990L);
		user.setUserEmailId("abcde@email");
		user.setUserName("abcde");
		userRepo.save(user);
		UserEntity actualResultUser =userRepo.findByUserContactNo(9999999990L);
		assertEquals(user.getUserContactNo(), actualResultUser.getUserContactNo());
	}
	
	
	  @AfterEach void tearDown() { userRepo.deleteAll(); }
	 
	
}
