package br.com.dashmottu.controller;

import br.com.dashmottu.model.dto.LocalizacaoDTO;
import br.com.dashmottu.model.dto.MotoRequestDTO;
import br.com.dashmottu.model.dto.MotoResponseDTO;
import br.com.dashmottu.model.entities.Moto;
import br.com.dashmottu.service.MotoService;
import jakarta.validation.Valid;
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
    public ResponseEntity<Object> post(@Valid @RequestBody MotoRequestDTO motoDTO) {
        try {
            Moto moto = service.salvar(motoDTO);
            return ResponseEntity.status(201)
                    .body(new MotoResponseDTO(moto.getId(),
                            moto.getCodTag(),
                            moto.getModelo(),
                            moto.getPlaca(),
                            moto.getStatus())
                    );
        } catch (Exception e) {
            if (e.getMessage().contains("ORA-00001")) return ResponseEntity.status(404).body("Moto já cadastrada.");
            else return ResponseEntity.internalServerError().body("Internal Server Error");
        }
    }

    @PutMapping()
    public ResponseEntity<Object> putLocalMoto(@RequestParam String codTag, @Valid @RequestBody LocalizacaoDTO localizacaoDTO) {
        Moto moto = service.salvarLocalizacao(codTag, localizacaoDTO);
        if (moto != null) return ResponseEntity.ok(moto);
        else return ResponseEntity.status(404).body("Não foi possível atualizar");
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> put(@PathVariable Long id, @Valid @RequestBody MotoRequestDTO motoRequestDTO) {
        Moto editar = service.editar(id, motoRequestDTO);
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
