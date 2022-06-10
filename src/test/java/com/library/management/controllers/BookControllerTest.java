package com.library.management.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.library.management.config.TestSecurityConfig;
import com.library.management.configuration.SecurityConfiguration;
import com.library.management.dto.ApiResponseDto;
import com.library.management.dto.BookDto;
import com.library.management.service.BookService;
import com.library.management.service.UserService;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
@SpringBootTest(classes = TestSecurityConfig.class)
@AutoConfigureMockMvc
/*@EnableAutoConfiguration(exclude = { SecurityConfiguration.class })*/
class BookControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private BookService bookService;

    private static ObjectMapper mapper = new ObjectMapper();

    @Test
    void addBookDetails() throws Exception {
        String url = "/book/add";

        when(bookService.addBookDetails(any())).thenReturn(ApiResponseDto.builder().status(HttpStatus.OK).message("test ok").build());
        BookDto bookDto = new BookDto();
        bookDto.setBookName("Test");
        bookDto.setAutherName("Test");
        String requestJson = mapper.writeValueAsString(bookDto);
        this.mockMvc.perform(MockMvcRequestBuilders.post(url)
                        .contentType(MediaType.APPLICATION_JSON).characterEncoding("utf-8").content(requestJson)).andExpect(status().isOk());




    }

    @Test
    void deleteBook() throws Exception {
        String url = "/delete/{id}";

        when(bookService.deleteBookDetails(2l)).thenReturn(ApiResponseDto.builder().status(HttpStatus.OK).message("test ok").build());

        this.mockMvc.perform(MockMvcRequestBuilders.delete(url, "2")
                .contentType(MediaType.APPLICATION_JSON).characterEncoding("utf-8")).andExpect(status().isOk());
    }

    @Test
    void getAllBooks() throws Exception {
        when(bookService.listAllBooks()).thenReturn(ApiResponseDto.builder().status(HttpStatus.OK).message("test ok").build());

        String url = "/book/all-books";
        this.mockMvc.perform(MockMvcRequestBuilders.get(url)
                .contentType(MediaType.APPLICATION_JSON).characterEncoding("utf-8")).andExpect(status().isOk());

    }

    @Test
    void issueBook() throws Exception {
        String url = "/book/issue";

        when(bookService.issueBook(any(),any())).thenReturn(ApiResponseDto.builder().status(HttpStatus.OK).message("test ok").build());

        this.mockMvc.perform(MockMvcRequestBuilders.get(url)
                .contentType(MediaType.APPLICATION_JSON).param("bookid", "2").param("libCardNo","3") .characterEncoding("utf-8")).andExpect(status().isOk());

    }

    @Test
    void submitBook() throws Exception {

        String url = "/book/submit";

        when(bookService.submitBook(any(),any())).thenReturn(ApiResponseDto.builder().status(HttpStatus.OK).message("test ok").build());

        this.mockMvc.perform(MockMvcRequestBuilders.get(url)
                .contentType(MediaType.APPLICATION_JSON).param("bookid", "2").param("libCardNo","3") .characterEncoding("utf-8")).andExpect(status().isOk());


    }
}