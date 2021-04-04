package ru.mirea.practice24.services;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.junit4.SpringRunner;
import ru.mirea.practice24.dto.SignUpRequest;
import ru.mirea.practice24.models.User;
import ru.mirea.practice24.repositories.UserRepository;


import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

import java.util.Optional;

@SpringBootTest
@RunWith(SpringRunner.class)
public class UserServiceTest {
    @MockBean
    private UserRepository userRepository;
    private UserService userService;
    private PasswordEncoder passwordEncoder;
    @Captor
    private ArgumentCaptor<User> captor;

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    @Autowired
    public void setPasswordEncoder(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }

    @Before
    public void setUp() {
        User user = new User();
        user.setUsername("user");
        user.setPassword("password");

        when(userRepository.findByUsername(user.getUsername()))
                .thenReturn(Optional.of(user));
    }

    @Test
    public void UserShouldBeRegister() {
        SignUpRequest request = new SignUpRequest("newUser", "qwerty", "qwerty");
        String encodedPassword = passwordEncoder.encode(request.getPassword());
        User newUser = new User();
        newUser.setUsername(request.getUsername());
        newUser.setPassword(encodedPassword);

        when(userRepository.save(any(User.class)))
                .thenReturn(newUser);
        User user = userService.registerNewUserAccount(request);

        assertThat(user).isNotNull();
        verify(userRepository).save(captor.capture());
        User captured = captor.getValue();
        assertEquals(request.getUsername(), captured.getUsername());
    }

    @Test
    public void UserShouldBeFound() {
        String username = "user";
        UserDetails userDetails = userService.loadUserByUsername(username);
        assertEquals(username, userDetails.getUsername());
    }

    @Test
    public void UserShouldBeExist() {
        String username = "user";
        boolean exist = userService.exist(username);
        assertTrue(exist);
    }
}
