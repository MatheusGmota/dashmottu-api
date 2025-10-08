package br.com.dashmottu.repository;

import br.com.dashmottu.model.entities.Localizacao;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface LocalizacaoRepository extends MongoRepository<Localizacao, String> {
    Optional<Localizacao> findTopByCodTagOrderByUltimaModificacaoDesc(String codTag);
}
