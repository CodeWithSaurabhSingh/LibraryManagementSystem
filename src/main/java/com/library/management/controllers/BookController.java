package com.library.management.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.library.management.dto.ApiResponseDto;
import com.library.management.dto.BookDto;
import com.library.management.service.BookService;

/**
 * @author Saurabh Singh
 *
 */
@RestController
@RequestMapping("/book")
public class BookController {

	@Autowired
	BookService bookservice;

	/**
	 * @param bookDtoDetails
	 * @return
	 */
	@PostMapping(value = "/add")
	public ApiResponseDto addBookDetails(@RequestBody BookDto bookDtoDetails) {
		return bookservice.addBookDetails(bookDtoDetails);
	}

	/**
	 * @param id
	 * @return
	 */
	@DeleteMapping("/delete/{id}")
	public ApiResponseDto deleteBook(@PathVariable Long id) {
		return bookservice.deleteBookDetails(id);
	}

	/**
	 * @return
	 */
	@GetMapping("all-books")
	public ApiResponseDto getAllBooks() {
		return bookservice.listAllBooks();
	}

	/**
	 * @param bookid
	 * @param libCardNo
	 * @return
	 */
	@GetMapping("issue")
	public ApiResponseDto issueBook(@RequestParam Long bookid, @RequestParam String libCardNo) {
		return bookservice.issueBook(bookid, libCardNo);
	}

	/**
	 * @param bookid
	 * @param libCardNo
	 * @return
	 */
	@GetMapping("submit")
	public ApiResponseDto submitBook(@RequestParam Long bookid, @RequestParam String libCardNo) {
		return bookservice.submitBook(bookid, libCardNo);
	}
}
