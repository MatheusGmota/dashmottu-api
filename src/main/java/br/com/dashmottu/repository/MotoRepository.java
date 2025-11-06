package br.com.dashmottu.repository;

import br.com.dashmottu.model.entities.Moto;
import br.com.dashmottu.model.entities.Patio;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface MotoRepository extends JpaRepository<Moto, Long> {
    Optional<Moto> findByPatioAndId(Patio patio, Long idMoto);

    Moto findByCodTag(String codTag);
}
