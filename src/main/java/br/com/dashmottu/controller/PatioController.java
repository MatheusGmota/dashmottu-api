package br.com.dashmottu.controller;

import br.com.dashmottu.model.OperationResult;
import br.com.dashmottu.model.dto.PatioDTO;
import br.com.dashmottu.model.entities.Patio;
import br.com.dashmottu.service.PatioService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/patio")
@CrossOrigin("*")
public class PatioController {

    @Autowired
    private PatioService service;

    @GetMapping
    public ResponseEntity<Object> obterTodos() {
        OperationResult<Object> result = service.listarTodos();
        return ResponseEntity.status(result.getStatusCode())
                .body((result.getErrorMessage() == null) ? result.getData() : result.getErrorMessage());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> getById(@PathVariable Long id) {
        OperationResult<Object> result = this.service.obterPorId(id);
        return ResponseEntity.status(result.getStatusCode())
                .body((result.getErrorMessage() == null) ? result.getData() : result.getErrorMessage());
    }

    @PostMapping
    public ResponseEntity<Object> post(@Valid @RequestBody PatioDTO patioDTO) {
        OperationResult<Object> result = this.service.salvar(patioDTO);
        return ResponseEntity.status(result.getStatusCode())
                .body((result.getErrorMessage() == null) ? result.getData() : result.getErrorMessage());
    }

    @PostMapping("/{id}/motos")
    public ResponseEntity<Object> postMoto(@PathVariable("id") Long id, @RequestParam("id-moto") Long idMoto) {
        OperationResult<Object> result = service.salvarMoto(id, idMoto);
        return ResponseEntity.status(result.getStatusCode())
                .body((result.getErrorMessage() == null) ? result.getData() : result.getErrorMessage());
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> put(@PathVariable Long id, @Valid @RequestBody PatioDTO patioDTO) {
        OperationResult<Object> result = service.editar(id, patioDTO);
        return ResponseEntity.status(result.getStatusCode())
                .body((result.getErrorMessage() == null) ? result.getData() : result.getErrorMessage());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> delete(@PathVariable Long id) {
        OperationResult<Object> result = this.service.deletar(id);
        return ResponseEntity.status(result.getStatusCode())
                .body((result.getErrorMessage() == null) ? result.getData() : result.getErrorMessage());
    }
}
