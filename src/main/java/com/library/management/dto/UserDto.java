package com.library.management.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserDto {
	private Long userId;
	private String userName;
	private String userEmailId;
	private Long userContactNo;
	private String libCardNo;
}
