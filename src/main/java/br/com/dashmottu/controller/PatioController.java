package br.com.dashmottu.controller;

import br.com.dashmottu.model.entities.Moto;
import br.com.dashmottu.model.entities.Patio;
import br.com.dashmottu.service.PatioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/patio")
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
    public ResponseEntity<Object> post(@RequestBody Patio patio) {
        Patio salvar = service.salvar(patio);
        return ResponseEntity.status(201).body(salvar);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> put(@PathVariable Long id, @RequestBody Patio patio) {
        Patio editar = service.editar(id, patio);
        if(editar != null) return ResponseEntity.ok(editar);
        else return ResponseEntity.status(404).body("Não foi possível atualizar");
    }

    @DeleteMapping("/id")
    public ResponseEntity<Object> delete(@PathVariable Long id) {
        String resposta = service.deletar(id);
        if(resposta != null) return ResponseEntity.ok(resposta);
        else return ResponseEntity.status(404).body("Objeto não existe");
    }
}
