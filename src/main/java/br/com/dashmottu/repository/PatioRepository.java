package br.com.dashmottu.repository;

import br.com.dashmottu.model.entities.Patio;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PatioRepository extends JpaRepository<Patio, Long> {
}
