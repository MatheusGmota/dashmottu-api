package br.com.dashmottu.service;

import br.com.dashmottu.model.entities.Patio;
import br.com.dashmottu.repository.PatioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PatioService {

    @Autowired
    private PatioRepository repository;

    public List<Patio> listarTodos() {
        return repository.findAll();
    }

}
