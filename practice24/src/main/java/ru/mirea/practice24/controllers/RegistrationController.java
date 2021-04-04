package ru.mirea.practice24.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.mirea.practice24.dto.SignUpRequest;
import ru.mirea.practice24.exceptions.UserAlreadyExistException;
import ru.mirea.practice24.services.UserService;

import javax.validation.Valid;
import java.util.Map;

@Controller
@RequiredArgsConstructor
@RequestMapping("registration")
public class RegistrationController {
    private final UserService userService;

    @GetMapping
    public String index() {
        return "registration";
    }

    @PostMapping
    public String add(@Valid @ModelAttribute("signUpRequest") SignUpRequest signUpRequest,
                      BindingResult result,
                      Map<String, Object> model) {
        if (!result.hasErrors()) {
            if (!signUpRequest.getPassword().equals(signUpRequest.getMatchingPassword())) {
                model.put("error", "The password and confirm password fields do not match");
                return "registration";
            }

            try {
                userService.registerNewUserAccount(signUpRequest);
            } catch (UserAlreadyExistException e) {
                model.put("error", e.getMessage());
                return "registration";
            }
        }

        return "redirect:/login";
    }
}
