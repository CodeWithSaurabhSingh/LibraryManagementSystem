package com.library.management.service;

import static org.junit.jupiter.api.Assertions.assertSame;
import static org.mockito.Mockito.verify;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import com.library.management.dto.ApiResponseDto;
import com.library.management.dto.IdCardDto;
import com.library.management.dto.UserDto;
import com.library.management.entity.UserEntity;
import com.library.management.repository.UserRepository;
import com.library.management.serviceImpl.UserServiceImpl;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.http.HttpStatus;


@ExtendWith(MockitoExtension.class)
public class UserServiceTest {

	@Mock
	private UserRepository userRepo;
	
	@Mock
	private UserDto userDtoDetails;
	
	@Mock
	private UserEntity userEntity;
	
	@Mock
	private ApiResponseDto apiresponsedto;
	
	@Mock
	private IdCardDto idcarddto;
	
	@Mock
	private UserServiceImpl userService;
	
	
	
	@BeforeEach
	void setUp() {
		this.userService=new UserServiceImpl(this.userRepo);
		
	}
	
	@Test
	void getUserDetailsBylIdCardNo() {
		
		userService.getUserDetails("abc9999999998");
		verify(userRepo).findByLibCardNo("abc9999999998");
	}
	
	@Test
	void addUserDetails() {

			UserDto userDtoDetails = new UserDto();
			userDtoDetails.setUserName("Test");
			userDtoDetails.setUserEmailId("abc@123");
			userDtoDetails.setUserContactNo(12345679L);
			userService.addUserDetails(userDtoDetails);
			IdCardDto idcarddto = new IdCardDto("Saurabh", "Sau9982107190");
			Mockito.when(userService.addUserDetails(userDtoDetails)).thenReturn(ApiResponseDto.builder().status(HttpStatus.OK).message("test ok").build());
			assertSame(userRepo.findByLibCardNo(userEntity.getLibCardNo()), idcarddto.getCardNumber());

	}
}
