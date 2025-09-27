package br.com.dashmottu.controller.api;

import br.com.dashmottu.model.dto.AuthenticationDTO;
import br.com.dashmottu.model.dto.SignupDTO;
import br.com.dashmottu.model.entities.User;
import br.com.dashmottu.repository.UserRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/auth")
public class AuthenticationController {

    @Autowired
    private UserRepository repository;

    @Autowired
    private AuthenticationManager authenticationManager;

    @PostMapping("/login")
    public ResponseEntity<Object> login(@RequestBody @Valid AuthenticationDTO data) {
        var usernamePassword = new UsernamePasswordAuthenticationToken(data.login(), data.password());
        var auth = this.authenticationManager.authenticate(usernamePassword);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/signup")
    public ResponseEntity<Object> signup(@RequestBody @Valid SignupDTO data) {
        if(this.repository.findByLogin(data.login()).isPresent()) return ResponseEntity.badRequest().build();

        String encode = new BCryptPasswordEncoder().encode(data.password());
        User newUser = new User(data.login(), encode, data.role());

        this.repository.save(newUser);

        return ResponseEntity.ok().build();
    }
}
