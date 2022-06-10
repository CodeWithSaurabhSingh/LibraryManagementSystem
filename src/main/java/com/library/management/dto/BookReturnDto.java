package com.library.management.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class BookReturnDto {

	private String libCardNo;
	private String bookName;
	private LocalDateTime issueDate;
	private LocalDateTime returnDate;
	private Long noOfExtraDays = 0L;
	private Long fineAmount = 0L;

}
