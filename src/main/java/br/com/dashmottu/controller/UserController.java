package br.com.dashmottu.controller;

import br.com.dashmottu.model.OperationResult;
import br.com.dashmottu.model.dto.AuthenticationDTO;
import br.com.dashmottu.model.dto.UserDto;
import br.com.dashmottu.model.entities.Patio;
import br.com.dashmottu.service.PatioService;
import br.com.dashmottu.service.UserService;
import jakarta.validation.Valid;
import jakarta.websocket.server.PathParam;
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
    UserService service;

    @GetMapping()
    public ResponseEntity<List<UserDto.Response>> getUsersByPatioId(@RequestParam("id-patio") Long idPatio) {
        Patio patio = this.patioService.obterPorId(idPatio);
        List<UserDto.Response> users = patio.getUsersDto();
        if (users.isEmpty()) return ResponseEntity.noContent().build();
        return ResponseEntity.ok(users);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> getUserById(@PathVariable("id") Long id) {
        try {
            OperationResult<Object> result = this.service.obterPorId(id);
            return ResponseEntity.status(result.getStatusCode())
                    .body((result.getErrorMessage() == null) ? result.getData() : result.getErrorMessage());
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body(e.getCause());
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> put(@Valid @RequestBody AuthenticationDTO user, @PathParam("id") Long id) {
        try {
            OperationResult<Object> result = this.service.editar(user, id);
            return ResponseEntity.status(result.getStatusCode()).body(
                    (result.getErrorMessage() == null) ? result.getData() : result.getErrorMessage()
            );
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body(e.getCause());
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> delete(@PathVariable("id") Long id) {
        try {
            OperationResult<Object> result = this.service.delete(id);
            return ResponseEntity.status(result.getStatusCode()).body(
                    (result.getErrorMessage() == null) ? result.getData() : result.getErrorMessage()
            );
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body(e.getCause());
        }
    }
}
