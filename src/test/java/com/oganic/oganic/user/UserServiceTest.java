package com.oganic.oganic.user;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.bean.override.mockito.MockitoBean;

@SpringBootTest
public class UserServiceTest {

    @Autowired
    private UserService userService;

    @MockitoBean
    private UserRepository userRepository;

    private User user;

    private RegisterRequest registerRequest;

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
    public void testFindByEmail() {

        when(userRepository.findByEmail("test@gmail.com")).thenReturn(user);

        User resultUser = userService.findByEmail(user.getEmail());

        assertEquals(user, resultUser);
    }

    @Test
    public void testSaveUserSuccess() {
        when(userRepository.existsByEmail(user.getEmail())).thenReturn(false);
        when(userRepository.save(any(User.class))).thenReturn(user);

        User newUser = userService.saveUser(registerRequest);

        assertEquals(user, newUser);
    }
    @Test
    public void testSaveUser_emailAlreadyExist() {
        when(userRepository.existsByEmail(user.getEmail())).thenReturn(true);

        User newUser = userService.saveUser(registerRequest);

        assertEquals(null, newUser);
    }
}
