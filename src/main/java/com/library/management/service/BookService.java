package com.library.management.service;

import com.library.management.dto.ApiResponseDto;
import com.library.management.dto.BookDto;

/**
 * @author Saurabh Singh
 *
 */
public interface BookService {

	/**
	 * @param bookDtoDetails
	 * @return
	 */
	ApiResponseDto addBookDetails(BookDto bookDtoDetails);

	/**
	 * @param id
	 * @return
	 */
	ApiResponseDto deleteBookDetails(Long id);

	/**
	 * @return
	 */
	ApiResponseDto listAllBooks();

	/**
	 * @param bookid
	 * @param libCardNo
	 * @return
	 */
	ApiResponseDto issueBook(Long bookid, String libCardNo);

	/**
	 * @param bookid
	 * @param libCardNo
	 * @return
	 */
	ApiResponseDto submitBook(Long bookid, String libCardNo);
}
