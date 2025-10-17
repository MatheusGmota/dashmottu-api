package br.com.dashmottu.controller;

import br.com.dashmottu.model.dto.UserDto;
import br.com.dashmottu.model.entities.Patio;
import br.com.dashmottu.repository.UserRepository;
import br.com.dashmottu.service.PatioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/usuarios")
@CrossOrigin("*")
public class UserController {

    @Autowired
    PatioService patioService;

    @Autowired
    UserRepository repository;

    @GetMapping()
    public ResponseEntity<List<UserDto>> getUsersByPatioId(@RequestParam("id-patio") Long idPatio) {
        Patio patio = this.patioService.obterPorId(idPatio);
        List<UserDto> users = patio.getUsersDto();
        if (users.isEmpty()) return ResponseEntity.noContent().build();
        return ResponseEntity.ok(users);
    }

}
