package br.com.dashmottu.controller;

import br.com.dashmottu.model.entities.Localizacao;
import br.com.dashmottu.repository.LocalizacaoRepository;
import jakarta.websocket.server.PathParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

@RestController
@RequestMapping("/api/localizacao")
public class LocalizacaoController {

    @Autowired
    private LocalizacaoRepository localizacaoRepository;

    @PostMapping("/iot")
    public ResponseEntity<String> receberLocalizacao(@RequestBody Localizacao localizacao) {
        localizacao.setUltimaModificacao(new Date());
        localizacaoRepository.save(localizacao);
        return ResponseEntity.ok("Localização recebida com sucesso!");
    }

    @GetMapping()
    public ResponseEntity<Localizacao> buscarUltimaLocalizacao(@PathParam("codTag") String codTag) {
        return localizacaoRepository
                .findTopByCodTagOrderByUltimaModificacaoDesc(codTag)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
}
