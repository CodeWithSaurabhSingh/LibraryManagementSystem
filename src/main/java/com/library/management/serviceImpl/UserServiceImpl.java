package com.library.management.serviceImpl;

import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.library.management.dto.ApiResponseDto;
import com.library.management.dto.ApiResponseDto.ApiResponseDtoBuilder;
import com.library.management.dto.IdCardDto;
import com.library.management.dto.UserDetailsDto;
import com.library.management.dto.UserDto;
import com.library.management.entity.UserEntity;
import com.library.management.repository.UserRepository;
import com.library.management.service.UserService;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	UserRepository userRepo;

	//for mock data in unit test
	public UserServiceImpl(UserRepository userRepo) {
		super();
		this.userRepo = userRepo;
	}

	@Override
	public ApiResponseDto addUserDetails(UserDto userDtoDetails) {

			if (Objects.isNull(userRepo.findByUserEmailId(userDtoDetails.getUserEmailId()))) {
					if (Objects.isNull(userRepo.findByUserContactNo(userDtoDetails.getUserContactNo()))) {
						UserEntity userEntity = new UserEntity();
						userEntity.setUserName(userDtoDetails.getUserName());
						userEntity.setUserEmailId(userDtoDetails.getUserEmailId());
						userEntity.setUserContactNo(userDtoDetails.getUserContactNo());
						userEntity.setLibCardNo(idGenrator(userDtoDetails.getUserName(), userDtoDetails.getUserContactNo()));
						userEntity = userRepo.save(userEntity);
						IdCardDto idcarddto = new IdCardDto(userEntity.getUserName(), userEntity.getLibCardNo());
						return ApiResponseDto.builder().status(HttpStatus.OK).message("Visitor Registered Successfully")
								.data(idcarddto).build();
					} else
						return ApiResponseDto.builder().status(HttpStatus.BAD_REQUEST).message("ContactNo Already Exist").build();
			} else
				return ApiResponseDto.builder().status(HttpStatus.BAD_REQUEST).message("EmailId Already Exist").build();
		}

	/* Library card number generator */
	private String idGenrator(String name, Long contactNo) {
		String str = name.substring(0, 3) + contactNo.toString();
		return str;
	}

	@Override
	public ApiResponseDto getUserDetails(String libCardNo) {
		if(Objects.nonNull(userRepo.findByLibCardNo(libCardNo))){
			UserEntity userEntity = userRepo.findByLibCardNo(libCardNo);
			UserDetailsDto userDetailsDto = new UserDetailsDto();
			userDetailsDto.setUserName(userEntity.getUserName());
			userDetailsDto.setUserEmailId(userEntity.getUserEmailId());
			userDetailsDto.setUserContactNo(userEntity.getUserContactNo());
			userDetailsDto.setLibCardNo(userEntity.getLibCardNo());
			userDetailsDto.setId(userEntity.getId());
			userDetailsDto.setBooks(userEntity.getBooks());
			return ApiResponseDto.builder().status(HttpStatus.OK).message(" User Details fetched").data(userDetailsDto).build();
			
		}else
		return ApiResponseDto.builder().status(HttpStatus.OK).message("User Doesn't Exist").build();
	}

}
