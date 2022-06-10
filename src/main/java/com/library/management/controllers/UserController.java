package com.library.management.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.library.management.dto.ApiResponseDto;
import com.library.management.dto.UserDto;
import com.library.management.service.UserService;

/**
 * @author Saurabh Singh
 *
 */
@RestController
@RequestMapping("user")
public class UserController {

	@Autowired
	UserService usersevice;

	/**
	 * @param userDtoDetails
	 * @return
	 */
	@PostMapping(value = "add")
	public ApiResponseDto addUserDetails(@RequestBody UserDto userDtoDetails) {
		return usersevice.addUserDetails(userDtoDetails);
	}

	@GetMapping(value = "getUserDetails")
	public ApiResponseDto getUserDetails(@RequestParam String libCardNo) {

		return usersevice.getUserDetails(libCardNo);
	}
}
