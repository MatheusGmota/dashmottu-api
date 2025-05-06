package br.com.dashmottu.controller;

import br.com.dashmottu.model.entities.Moto;
import br.com.dashmottu.service.MotoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping("/moto")
public class MotoController {

    @Autowired
    private MotoService service;

    @GetMapping
    public ResponseEntity<Object> get() {
        List<Moto> lista = service.listarTodos();
        return ResponseEntity.ok(lista);
    }
}
