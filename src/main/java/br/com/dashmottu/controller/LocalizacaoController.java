package br.com.dashmottu.controller;

import br.com.dashmottu.model.entities.Localizacao;
import br.com.dashmottu.repository.LocalizacaoRepository;
import br.com.dashmottu.service.LocalizacaoService;
import jakarta.websocket.server.PathParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

@RestController
@RequestMapping("/api/localizacao")
@CrossOrigin("*")
public class LocalizacaoController {

    @Autowired
    private LocalizacaoService service;

    @PostMapping("/iot")
    public ResponseEntity<String> receberLocalizacao(@RequestBody Localizacao localizacao) {
        localizacao.setUltimaModificacao(new Date());
        Object save = this.service.save(localizacao);
        if (save == null) return ResponseEntity.status(400).body("Moto não localizada");
        return ResponseEntity.ok("Localização recebida com sucesso!");
    }

    @GetMapping()
    public ResponseEntity<Localizacao> buscarUltimaLocalizacao(@PathParam("codTag") String codTag) {
        return service.buscarUltimaLocalizacao(codTag)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
}
