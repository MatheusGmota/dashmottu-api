package br.com.dashmottu.repository;

import br.com.dashmottu.model.entities.Moto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MotoRepository extends JpaRepository<Moto, Long> {

    Moto findByCodTag(String codTag);
}
