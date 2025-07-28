package com.oganic.oganic.user;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@SpringBootTest
@AutoConfigureMockMvc
public class UserControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private UserService userService;

    private RegisterRequest registerRequest;

    private User user;

    private String jsonRequest;

    @BeforeEach
    void setup() {
        registerRequest = RegisterRequest.builder()
                .email("test@gmail.com")
                .firstName("Hello")
                .lastName("World")
                .password("test123456")
                .phone("0123456789")
                .address("Nagoya")
                .build();

        jsonRequest = """
                {
                "email": "test@gmail.com",
                "firstName": "Hello",
                "lastName": "World",
                "password": "test123456",
                "phone": "0123456789",
                "address": "Nagoya"
                }
                """;

        user = User.builder()
                .email("test@gmail.com")
                .firstName("Hello")
                .lastName("World")
                .password("test123456")
                .phone("0123456789")
                .address("Nagoya")
                .build();

        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder(10);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
    }

    @Test
    public void saveUser_Success() throws Exception {
        Mockito.when(userService.saveUser(Mockito.any(RegisterRequest.class))).thenReturn(user);

        mockMvc.perform(MockMvcRequestBuilders.post("/api/users/register").contentType(MediaType.APPLICATION_JSON)
                .content(jsonRequest))
                .andExpect(MockMvcResultMatchers.status().isCreated())
                .andExpect(MockMvcResultMatchers.jsonPath("$.success").value(true))
                .andExpect(MockMvcResultMatchers.jsonPath("$.data.email").value(user.getEmail()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.data.firstName").value(user.getFirstName()));

    }

    @Test
    public void saveUser_invalidEmail() throws Exception {
        jsonRequest = """
                {
                "email": "testgmail.com",
                "firstName": "Hello",
                "lastName": "World",
                "password": "test123456",
                "phone": "0123456789",
                "address": "Nagoya"
                }
                """;
        mockMvc.perform(MockMvcRequestBuilders.post("/api/users/register").contentType(MediaType.APPLICATION_JSON)
                .content(jsonRequest))
                .andExpect(MockMvcResultMatchers.status().isBadRequest())
                .andExpect(MockMvcResultMatchers.jsonPath("$.success").value(false))
                .andExpect(MockMvcResultMatchers.jsonPath("$.message").value("Validate failed"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.data.email").value("Email is not valid"));
    }

    @Test
    public void saveUser_firstNameEmpty() throws Exception {
        jsonRequest = """
                {
                "email": "test@gmail.com",
                "firstName": "",
                "lastName": "World",
                "password": "test123456",
                "phone": "0123456789",
                "address": "Nagoya"
                }
                """;
        mockMvc.perform(MockMvcRequestBuilders.post("/api/users/register").contentType(MediaType.APPLICATION_JSON)
                .content(jsonRequest))
                .andExpect(MockMvcResultMatchers.status().isBadRequest())
                .andExpect(MockMvcResultMatchers.jsonPath("$.success").value(false))
                .andExpect(MockMvcResultMatchers.jsonPath("$.message").value("Validate failed"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.data.firstName").value("First name must not be blank"));
    }

    @Test
    public void login_Success() throws Exception {
        jsonRequest = """
                {
                "email": "test@gmail.com",
                "password": "test123456"
                }
                """;
        Mockito.when(userService.findByEmail(registerRequest.getEmail())).thenReturn(user);

        mockMvc.perform(MockMvcRequestBuilders.post("/api/users/login").contentType(MediaType.APPLICATION_JSON)
                .content(jsonRequest))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.success").value(true))
                .andExpect(MockMvcResultMatchers.jsonPath("$.message").value("Login success"));
    }

    @Test
    public void loginFalse_EmailNotExist() throws Exception {
        jsonRequest = """
                {
                "email": "test@gmail.com",
                "password": "test123456"
                }
                """;
        Mockito.when(userService.findByEmail(registerRequest.getEmail())).thenReturn(null);

        mockMvc.perform(MockMvcRequestBuilders.post("/api/users/login").contentType(MediaType.APPLICATION_JSON)
                .content(jsonRequest))
                .andExpect(MockMvcResultMatchers.status().isUnauthorized())
                .andExpect(MockMvcResultMatchers.jsonPath("$.success").value(false))
                .andExpect(MockMvcResultMatchers.jsonPath("$.message").value("Invalid email or password"));
    }
}
