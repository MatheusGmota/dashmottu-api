package br.com.dashmottu.controller;

import br.com.dashmottu.model.OperationResult;
import br.com.dashmottu.model.dto.AuthenticationDTO;
import br.com.dashmottu.model.dto.RegisterDTO;
import br.com.dashmottu.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("auth")
@CrossOrigin("*")
public class AuthController {

    @Autowired
    UserService userService;

    @PostMapping("/login")
    public ResponseEntity<Object> login(@RequestBody @Valid AuthenticationDTO data) {
        try {
            OperationResult<Object> result = this.userService.login(data);
            return ResponseEntity.status(result.statusCode)
                    .body((result.errorMessage == null) ? result.data : result.errorMessage);
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body(e.getCause());
        }
    }

    @PostMapping("/register")
    public ResponseEntity<Object> register(@RequestBody @Valid RegisterDTO data, @RequestParam(value = "id-patio", required = false) Long idPatio) {
        try {
            OperationResult<Object> result = this.userService.register(data, idPatio);
            return ResponseEntity.status(result.statusCode)
                    .body((result.errorMessage == null) ? result.data : result.errorMessage);
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body(e.getCause());
        }
    }
}
