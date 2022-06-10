package com.library.management.service;

import com.library.management.dto.ApiResponseDto;
import com.library.management.dto.UserDto;

/**
 * @author Saurabh Singh
 *
 */
public interface UserService {
	
	ApiResponseDto addUserDetails(UserDto userDtoDetails);

	ApiResponseDto getUserDetails(String libCardNo);

}
