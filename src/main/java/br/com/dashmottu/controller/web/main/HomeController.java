package br.com.dashmottu.controller.web.main;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import java.security.Principal;

@Controller
@RequestMapping("/")
@EnableWebMvc
public class HomeController {

    @GetMapping
    public String index(Principal principal) {
        return "home";
    }
}
