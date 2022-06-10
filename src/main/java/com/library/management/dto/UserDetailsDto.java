package com.library.management.dto;

import java.util.List;

import com.library.management.entity.BookEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserDetailsDto {

	private Long id;
	private String userName;
	private String userEmailId;
	private Long userContactNo;
	private String libCardNo;
	private List<BookEntity> books;

}
