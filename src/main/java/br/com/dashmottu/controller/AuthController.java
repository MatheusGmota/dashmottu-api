package br.com.dashmottu.controller;

import br.com.dashmottu.infra.security.TokenService;
import br.com.dashmottu.model.dto.AuthenticationDTO;
import br.com.dashmottu.model.dto.LoginResponseDto;
import br.com.dashmottu.model.dto.RegisterDTO;
import br.com.dashmottu.model.entities.User;
import br.com.dashmottu.repository.UserRepository;
import br.com.dashmottu.service.PatioService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("auth")
@CrossOrigin("*")
public class AuthController {

//    private OperationResult operationResult;

    @Autowired
    private TokenService tokenService;

    @Autowired
    private PatioService patioService;

    @Autowired
    private UserRepository repository;

    @Autowired
    private AuthenticationManager authenticationManager;

    @PostMapping("/login")
    public ResponseEntity<Object> login(@RequestBody @Valid AuthenticationDTO data) {
        var usernamePassword = new UsernamePasswordAuthenticationToken(data.login(), data.password());
        var auth = this.authenticationManager.authenticate(usernamePassword);

        var token = tokenService.generateToken((User) auth.getPrincipal());

        return ResponseEntity.ok(new LoginResponseDto(token));
    }

    @PostMapping("/register")
    public ResponseEntity<Object> register(@RequestBody @Valid RegisterDTO data, @RequestParam(value = "id-patio", required = false) Long idPatio) {
        if(this.repository.findByLogin(data.login()) != null) return ResponseEntity.status(400).body("Usuário já existe");

        String encryptedPassword = new BCryptPasswordEncoder().encode(data.password());
        User newUser = new User(data.login(), encryptedPassword, data.role());


        if (idPatio != null) {
            Object o = patioService.salvarUsuario(newUser, idPatio);
            if (o == null) return ResponseEntity.status(400).body("Pátio não encontrado");
//           operationResult.success("Usuário criado no pátio " + idPatio);
//          return ResponseEntity.status(operationResult.getStatusCode(), operationResult.getMessage());
            return ResponseEntity.ok().build();
        }
        this.repository.save(newUser);

//      return ResponseEntity.status(operationResult.getStatusCode(), operationResult.getMessage());
        return ResponseEntity.ok().build();
    }
}
