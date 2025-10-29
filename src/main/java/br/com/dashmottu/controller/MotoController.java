package br.com.dashmottu.controller;

import br.com.dashmottu.model.OperationResult;
import br.com.dashmottu.model.dto.MotoRequestDTO;
import br.com.dashmottu.model.dto.MotoResponseDTO;
import br.com.dashmottu.model.entities.Moto;
import br.com.dashmottu.service.MotoService;
import jakarta.validation.Valid;
import jakarta.websocket.server.PathParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/moto")
@CrossOrigin("*")
public class MotoController {

    @Autowired
    private MotoService service;

    @GetMapping
    public ResponseEntity<Object> get() {

        OperationResult<Object> result = this.service.listarTodos();
        return ResponseEntity.status(result.getStatusCode())
                .body((result.getErrorMessage() == null) ? result.getData() : result.getErrorMessage());

    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> getById(@PathVariable Long id) {

        OperationResult<Object> result = this.service.obterPorId(id);
        return ResponseEntity.status(result.getStatusCode())
                .body((result.getErrorMessage() == null) ? result.getData() : result.getErrorMessage());

    }

    @GetMapping("/patio")
    public ResponseEntity<Object> getMotosByPatioId(@PathParam("id-patio") Long idPatio) {

        OperationResult<Object> result = this.service.obterMotosPorPatioId(idPatio);
        return ResponseEntity.status(result.getStatusCode())
                .body((result.getErrorMessage() == null) ? result.getData() : result.getErrorMessage());

    }

    @PostMapping()
    public ResponseEntity<Object> post(@Valid @RequestBody MotoRequestDTO motoDTO) {

        OperationResult<Object> result = this.service.salvar(motoDTO);
        return ResponseEntity.status(result.getStatusCode())
                .body((result.getErrorMessage() == null) ? result.getData() : result.getErrorMessage());

    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> put(@PathVariable Long id, @Valid @RequestBody MotoRequestDTO motoRequestDTO) {

        OperationResult<Object> result = this.service.editar(id, motoRequestDTO);
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
