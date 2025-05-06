package br.com.dashmottu.service;

import br.com.dashmottu.model.entities.Moto;
import br.com.dashmottu.repository.MotoRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class MotoService {

    @Autowired
    private MotoRepository repository;

    public List<Moto> listarTodos() {
        return repository.findAll();
    }
}
