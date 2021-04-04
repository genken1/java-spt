package ru.mirea.practice24.services;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.mirea.practice24.dto.SignUpRequest;
import ru.mirea.practice24.exceptions.UserAlreadyExistException;
import ru.mirea.practice24.models.User;
import ru.mirea.practice24.models.UserPrincipal;
import ru.mirea.practice24.repositories.UserRepository;

@Service
@Slf4j
public class UserService implements UserDetailsService {
    private UserRepository userRepository;
    private PasswordEncoder passwordEncoder;
    private EmailService emailService;

    @Transactional
    public User registerNewUserAccount(SignUpRequest request) throws UserAlreadyExistException {
        if (exist(request.getUsername())) {
            throw new UserAlreadyExistException(
                    "There is an account with that username: "
                            + request.getUsername());
        }

        User user = new User();
        user.setUsername(request.getUsername());
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        log.info("Save user");
        emailService.sendEmail("Save user", user.getUsername());
        return userRepository.save(user);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found with username: " + username));
        return new UserPrincipal(user);
    }

    public boolean exist(String username) {
        return userRepository.findByUsername(username).isPresent();
    }

    @Autowired
    public void setUserRepository(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Autowired
    public void setPasswordEncoder(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }

    @Autowired
    public void setEmailService(EmailService emailService) {
        this.emailService = emailService;
    }
}
