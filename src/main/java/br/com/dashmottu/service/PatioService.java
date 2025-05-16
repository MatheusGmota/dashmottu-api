package br.com.dashmottu.service;

import br.com.dashmottu.model.entities.Moto;
import br.com.dashmottu.model.entities.Patio;
import br.com.dashmottu.repository.EnderecoRepository;
import br.com.dashmottu.repository.MotoRepository;
import br.com.dashmottu.repository.PatioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PatioService {

    @Autowired
    private PatioRepository repository;

    @Autowired
    private EnderecoRepository enderecoRepository;

    @Autowired
    private MotoRepository motoRepository;

    public List<Patio> listarTodos() {
        return repository.findAll();
    }

    public Patio obterPorId(Long id) {
        return repository.findById(id).orElse(null);
    }

    public Patio salvar(Patio patio) {
        enderecoRepository.save(patio.getEndereco());
        return repository.save(patio);
    }

    public Object salvarMoto(Long id, Long idMoto) {
        if (repository.existsById(id) && motoRepository.existsById(idMoto)) {
            Patio patio = repository.findById(id).orElse(null);
            Moto moto = motoRepository.findById(idMoto).orElse(null);
            if (patio != null && moto != null) {
                patio.addMoto(moto); //adicionando moto no contexto da lista do patio
                motoRepository.saveAndFlush(moto);
                return repository.saveAndFlush(patio); // atualizando tabelas
            }
        }
        return null;
    }

    public Patio editar(Long id, Patio patio) {
        if(repository.existsById(id)) {
            patio.setId(id);
            enderecoRepository.saveAndFlush(patio.getEndereco());
            return repository.saveAndFlush(patio);
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
