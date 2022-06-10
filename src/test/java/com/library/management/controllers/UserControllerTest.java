package com.library.management.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.library.management.config.TestSecurityConfig;
import com.library.management.dto.ApiResponseDto;
import com.library.management.dto.UserDto;
import com.library.management.service.BookService;
import com.library.management.service.UserService;
import com.library.management.serviceImpl.UserServiceImpl;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
@SpringBootTest(classes = TestSecurityConfig.class)
@AutoConfigureMockMvc
class UserControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UserService userService;

    private static ObjectMapper mapper = new ObjectMapper();


    @Test
    void addUserDetails() throws Exception {
           String url = "/user/add";
           when(userService.addUserDetails(any())).thenReturn(ApiResponseDto.builder().status(HttpStatus.OK).message("test ok").build());

           UserDto userDtoDetails = new UserDto();
           userDtoDetails.setUserName("Test");
           userDtoDetails.setUserEmailId("abc@123");
           userDtoDetails.setUserContactNo(12345679L);

           String requestJson = mapper.writeValueAsString(userDtoDetails);
           this.mockMvc.perform(MockMvcRequestBuilders.post(url)
                   .contentType(MediaType.APPLICATION_JSON).characterEncoding("utf-8").content(requestJson)).andExpect(status().isOk());

    }

    @Test
    void getUserDetails() throws Exception {
            when(userService.getUserDetails(any())).thenReturn(ApiResponseDto.builder().status(HttpStatus.OK).message("test ok").build());

            String url = "/user/getUserDetails";
            this.mockMvc.perform(MockMvcRequestBuilders.get(url)
                    .contentType(MediaType.APPLICATION_JSON).param("libCardNo", "abc1234").characterEncoding("utf-8")).andExpect(status().isOk());
    }
}