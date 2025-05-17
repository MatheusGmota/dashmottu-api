package br.com.dashmottu.controller;

import br.com.dashmottu.model.dto.PatioDTO;
import br.com.dashmottu.model.entities.Patio;
import br.com.dashmottu.service.PatioService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.DefaultHandlerExceptionResolver;

import java.util.List;

@RestController
@RequestMapping("/api/patio")
public class PatioController {

    @Autowired
    private PatioService service;

    @GetMapping
    public ResponseEntity<Object> obterTodos() {
        List<Patio> lista = service.listarTodos();
        return ResponseEntity.ok(lista);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> getById(@PathVariable Long id) {
        Patio patio = service.obterPorId(id);
        if (patio != null) return ResponseEntity.ok(patio);
        else return ResponseEntity.status(404).body("Objeto não existe");
    }

    @PostMapping
    public ResponseEntity<Object> post(@Valid @RequestBody PatioDTO patioDTO) {
        Patio salvar = service.salvar(patioDTO);
        return ResponseEntity.status(201).body(salvar);
    }

    @PostMapping("/{id}/motos")
    public ResponseEntity<Object> postMoto(@PathVariable("id") Long id, @RequestParam("id") Long idMoto) {
        Object o = service.salvarMoto(id, idMoto);
        if (o != null) return ResponseEntity.status(201).body(o);
        else return ResponseEntity.status(400).body("Erro na requisicão");
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> put(@PathVariable Long id, @Valid @RequestBody PatioDTO patioDTO) {
        Patio editar = service.editar(id, patioDTO);
        if(editar != null) return ResponseEntity.ok(editar);
        else return ResponseEntity.status(404).body("Não foi possível atualizar");
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> delete(@PathVariable Long id) {
        String resposta = service.deletar(id);
        if(resposta != null) return ResponseEntity.ok(resposta);
        else return ResponseEntity.status(404).body("Objeto não existe");
    }
}
