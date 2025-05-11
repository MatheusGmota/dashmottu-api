package br.com.dashmottu.service;

import br.com.dashmottu.model.entities.Localizacao;
import br.com.dashmottu.model.entities.Moto;
import br.com.dashmottu.repository.LocalizacaoRepository;
import br.com.dashmottu.repository.MotoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class MotoService {

    @Autowired
    private MotoRepository repository;

    @Autowired
    private LocalizacaoRepository localizacaoRepository;

    public List<Moto> listarTodos() {
        return repository.findAll();
    }

    public Moto obterPorId(Long id) {
        return repository.findById(id).orElse(null);
    }

    public Moto obterPorTag(String codTag) {
        return repository.findByCodTag(codTag);
    }

    public Moto salvarLocalizacao(String codTag, Localizacao localizacao) {
        Moto moto = obterPorTag(codTag);
        if (moto != null) {
            moto.setLocalizacao(localizacao);
            localizacao.setUltimaModificacao(new Date());
            localizacaoRepository.save(localizacao);
            return repository.save(moto);
        }
        return null;
    }

    public Moto salvar(Moto moto) {
        return repository.save(moto);
    }

    public Moto editar(Long id, Moto moto) {
        if(repository.existsById(id)) {
            moto.setId(id);
            return repository.save(moto);
        }
        return null;
    }

    public String deletar(Long id) {
        if(repository.existsById(id)) {
            repository.deleteById(id);
            return "Deletado com sucesso!";
        }
        return null;
    }
}
