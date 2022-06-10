package com.library.management.serviceImpl;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.library.management.dto.ApiResponseDto;
import com.library.management.dto.ApiResponseDto.ApiResponseDtoBuilder;
import com.library.management.dto.BookDto;
import com.library.management.dto.BookReturnDto;
import com.library.management.entity.BookEntity;
import com.library.management.entity.UserEntity;
import com.library.management.repository.BookRepository;
import com.library.management.repository.UserRepository;
import com.library.management.service.BookService;

@Service
public class BookServiceImpl implements BookService {

	@Autowired
	BookRepository bookRepo;

	@Autowired
	UserRepository userRepo;

	//for mock data in unit test
			public BookServiceImpl(BookRepository bookRepo) {
				super();
				this.bookRepo = bookRepo;
			}
	
	@Override
	public ApiResponseDto addBookDetails(BookDto bookDtoDetails) {
		BookEntity bookEntity = new BookEntity();
		bookEntity.setBookName(bookDtoDetails.getBookName());
		bookEntity.setAutherName(bookDtoDetails.getAutherName());
		BookEntity savedEntity = bookRepo.save(bookEntity);
		if (Objects.nonNull(savedEntity)) {
			return ApiResponseDto.builder().status(HttpStatus.OK).message("Book Added Succesfully").build();
		}
		return ApiResponseDto.builder().status(HttpStatus.BAD_REQUEST).message("Something went Wrong").build();
	}

	@Override
	public ApiResponseDto deleteBookDetails(Long id) {
		if(bookRepo.existsById(id)) {
			BookEntity bookEntity=bookRepo.findById(id).get();
			if(bookEntity.getStatus()) {
		bookRepo.deleteById(id);
		return ApiResponseDto.builder().status(HttpStatus.OK).message("Book Deleted Successfully").build();
		}else return ApiResponseDto.builder().status(HttpStatus.BAD_REQUEST).message("Book Already Issued").build();
	}else
		return ApiResponseDto.builder().status(HttpStatus.NOT_FOUND).message("Book not Exist").build();
	}

	@Override 
	public ApiResponseDto listAllBooks() {
		List<BookEntity> bookEntity = bookRepo.findAll();
		List<BookDto> booklist = bookEntity.stream()
				.map(o -> new BookDto(o.getId(),o.getBookName(),o.getAutherName(),o.getStatus())).collect(Collectors.toList());
		return ApiResponseDto.builder(). status(HttpStatus.OK).message("Available Books").data(booklist).build();
	}

	@Override
	public ApiResponseDto issueBook(Long bookid, String libCardNo) {
		BookEntity bookEntity = bookRepo.findById(bookid).get();
		if (Objects.nonNull(bookEntity)) {
			if (bookEntity.getStatus()) {
				UserEntity userEntity = userRepo.findByLibCardNo(libCardNo);
				if (Objects.nonNull(userEntity)) {
					bookEntity.setBorrower(userEntity);
					bookEntity.setBorrowDate(LocalDateTime.now());
					bookEntity.setStatus(false);
					bookRepo.save(bookEntity);
					return ApiResponseDto.builder().status(HttpStatus.OK).message("Book Issued Succesfully and please return Before "
							+ bookEntity.getBorrowDate().plusDays(30)).build();
					}
				else return ApiResponseDto.builder().status(HttpStatus.BAD_REQUEST).message("Libreary Card Invalid").build();
				
			}
			else return ApiResponseDto.builder().status(HttpStatus.BAD_REQUEST).message("Book Already Issued").build();
		}
		else return ApiResponseDto.builder().status(HttpStatus.BAD_REQUEST).message("Book not exist").build();
	}

	@Override
	public ApiResponseDto submitBook(Long bookid, String libCardNo) {
		BookEntity bookEntity = bookRepo.findById(bookid).get();
		if (Objects.nonNull(bookEntity)) {
			if (!bookEntity.getStatus()) {
				UserEntity userEntity = userRepo.findByLibCardNo(libCardNo);
				if (Objects.nonNull(userEntity)) {
					BookReturnDto bookreturndto = new BookReturnDto();
					bookreturndto.setLibCardNo(libCardNo);
					Long totalDays = ChronoUnit.DAYS.between(bookEntity.getBorrowDate(), LocalDateTime.now());
					if (totalDays > 30) {
						bookreturndto.setNoOfExtraDays(totalDays - 30);
						bookreturndto.setFineAmount((totalDays - 30) * 100);
					}
					bookreturndto.setBookName(bookEntity.getBookName());
					bookreturndto.setIssueDate(bookEntity.getBorrowDate());
					bookreturndto.setReturnDate(LocalDateTime.now());
					bookEntity.setBorrower(null);
					bookEntity.setBorrowDate(null);
					bookEntity.setStatus(true);
					bookRepo.save(bookEntity);
					return ApiResponseDto.builder().status(HttpStatus.OK).message("Book Submitted Succesfully").data(bookreturndto).build();

				}else return ApiResponseDto.builder().status(HttpStatus.BAD_REQUEST).message("Libreary Card Invalid").build();
			}else return ApiResponseDto.builder().status(HttpStatus.BAD_REQUEST).message("Book Already Submitted").build();
		}else return ApiResponseDto.builder().status(HttpStatus.BAD_REQUEST).message("Book not exist").build();
	}

	
}