package com.library.management.service;

import static org.mockito.Mockito.verify;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.library.management.repository.BookRepository;
import com.library.management.repository.UserRepository;
import com.library.management.serviceImpl.BookServiceImpl;

@ExtendWith(MockitoExtension.class)
public class BookServiceTest {
	
	@Mock
	BookRepository bookRepo;

	@Mock
	UserRepository userRepo;
	
	private BookService bookservice;
	
	@BeforeEach
	void setUp() {
		this.bookservice= new BookServiceImpl(this.bookRepo);
	}
	
	@Test
	void getAllBooks() {
		bookservice.listAllBooks();
		verify(bookRepo).findAll();
	}
	
}
