package br.com.dashmottu.controller;

import br.com.dashmottu.repository.MotoRepositoryProcedure;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/relatorio-banco")
public class RelatoriosController {

    @Autowired
    private MotoRepositoryProcedure repositoryProcedure;

    @GetMapping("/motos-patio")
    public ResponseEntity<Object> relatorioMotosPorPatio() {
        try {
            return ResponseEntity.ok(
             repositoryProcedure.buscarMotosPorPatio());
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("/exportar-dataset")
    public ResponseEntity<Object> exportarDataset() {
        try {
            return ResponseEntity.ok(
             repositoryProcedure.exportarDataset());
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
