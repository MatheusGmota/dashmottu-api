package br.com.dashmottu.controller.web.main;

import br.com.dashmottu.model.dto.AuthenticationDTO;
import br.com.dashmottu.repository.UserRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@Controller
@EnableWebMvc
public class LoginController {

    @Autowired
    private UserRepository repository;

    @Autowired
    private AuthenticationManager authenticationManager;

    @GetMapping("/login")
    public String login() {
        return "login";
    }

//    @GetMapping("/signup")
//    public String signup(Model model) {
//        model.addAttribute(new SignupDTO());
//        return "signup";
//    }

    @PostMapping("/login")
    public String login(@Valid @ModelAttribute("data") AuthenticationDTO data, BindingResult result) {
        if (result.hasErrors())
            return "login";
        var usernamePassword = new UsernamePasswordAuthenticationToken(data.login(), data.password());
        var auth = this.authenticationManager.authenticate(usernamePassword);

        SecurityContextHolder.getContext().setAuthentication(auth);

        return "redirect:/";
    }
}
