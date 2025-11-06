package br.com.dashmottu.service;

import br.com.dashmottu.model.entities.Localizacao;
import br.com.dashmottu.model.entities.Moto;
import br.com.dashmottu.repository.LocalizacaoRepository;
import br.com.dashmottu.repository.MotoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class LocalizacaoService {

    @Autowired
    private LocalizacaoRepository repository;

    @Autowired
    private MotoRepository motoRepository;

    public Object save(Localizacao localizacao) {
        Moto moto = motoRepository.findByCodTag(localizacao.getCodTag());
        if (moto == null) return null;
        return repository.save(localizacao);
    }

    public Optional<Localizacao> buscarUltimaLocalizacao(String codTag) {
        return repository.findTopByCodTagOrderByUltimaModificacaoDesc(codTag);
    }
}
