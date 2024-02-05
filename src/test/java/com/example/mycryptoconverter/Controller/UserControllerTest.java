package com.example.mycryptoconverter.Controller;

import com.example.mycryptoconverter.controllers.UserController;
import com.example.mycryptoconverter.dto.UserDto;
import com.example.mycryptoconverter.mappers.UserMapper;
import com.example.mycryptoconverter.services.UserService;
import com.example.mycryptoconverter.utils.BindingResultChecker;
import com.example.mycryptoconverter.validators.UserRegistrationValidator;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MockMvcBuilder;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@ExtendWith(MockitoExtension.class)
public class UserControllerTest {

    @Mock
    private UserService userService;
    @Mock
    private UserRegistrationValidator userValidator;
    @Mock
    private BindingResultChecker bindingResultChecker;
    @Mock
    private UserMapper userMapper;

    @InjectMocks
    private UserController userController;
    private MockMvc mockMvc;
    private ObjectMapper objectMapper;

    @BeforeEach
    void setUp(){
        mockMvc = MockMvcBuilders.standaloneSetup(userController).build();
        objectMapper = new ObjectMapper();
    }

    @Test
    void getHello() throws Exception {
        mockMvc.perform(get("/user/hello")).andExpect(status().isOk());
    }

    @Test
    void registerUser() throws Exception {
        UserDto userDto = UserDto.builder().username("MIsha").password("12345678").confirmPassword("12345678").build();
        String jsonUserDto = objectMapper.writeValueAsString(userDto);
        mockMvc.perform(post("/user/register")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonUserDto))
                        .andExpect(status().isOk());
    }

}
