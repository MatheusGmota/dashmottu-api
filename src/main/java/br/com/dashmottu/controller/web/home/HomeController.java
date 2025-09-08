package br.com.dashmottu.controller.web.home;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.security.Principal;

@Controller
@RequestMapping()
public class HomeController {

    @GetMapping("/")
    public String index(Principal principal) {
        return "home";
    }
}
