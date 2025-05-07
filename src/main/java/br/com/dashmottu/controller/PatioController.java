package br.com.dashmottu.controller;

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

    @PostMapping
    @RequestMapping("/{id}/motos")
    public ResponseEntity<Object> postMoto(@PathVariable("id") Long id, @RequestParam("id") Long idMoto) {
        return ResponseEntity.ok(id);
    };
}
