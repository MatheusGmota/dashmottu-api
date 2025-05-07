package br.com.dashmottu.service;

import br.com.dashmottu.model.entities.Moto;
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

    public Patio obterPorId(Long id) {
        return repository.findById(id).orElse(null);
    }

    public Patio salvar(Patio patio) {
        return repository.save(patio);
    }

    public Patio editar(Long id, Patio patio) {
        if(repository.existsById(id)) {
            patio.setId(id);
            return repository.save(patio);
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
