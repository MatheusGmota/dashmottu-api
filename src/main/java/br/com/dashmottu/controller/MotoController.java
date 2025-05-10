package br.com.dashmottu.controller;

import br.com.dashmottu.model.entities.Moto;
import br.com.dashmottu.service.MotoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/moto")
public class MotoController {

    @Autowired
    private MotoService service;

    @GetMapping
    public ResponseEntity<Object> get() {
        List<Moto> lista = service.listarTodos();
        return ResponseEntity.ok(lista);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> getById(@PathVariable Long id) {
        Moto moto = service.obterPorId(id);
        if (moto != null) return ResponseEntity.ok(moto);
        else return ResponseEntity.status(404).body("Objeto não existe");
    }

    @PostMapping()
    public ResponseEntity<Object> post(@RequestBody Moto moto) {
        Moto salvar = service.salvar(moto);
        return ResponseEntity.status(201).body(salvar);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> put(@PathVariable Long id, @RequestBody Moto moto) {
        Moto editar = service.editar(id, moto);
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
