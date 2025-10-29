package br.com.dashmottu.service;

import br.com.dashmottu.model.OperationResult;
import br.com.dashmottu.model.dto.PatioDTO;
import br.com.dashmottu.model.entities.Endereco;
import br.com.dashmottu.model.entities.Moto;
import br.com.dashmottu.model.entities.Patio;
import br.com.dashmottu.model.entities.User;
import br.com.dashmottu.repository.EnderecoRepository;
import br.com.dashmottu.repository.MotoRepository;
import br.com.dashmottu.repository.PatioRepository;
import br.com.dashmottu.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PatioService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PatioRepository repository;

    @Autowired
    private EnderecoRepository enderecoRepository;

    @Autowired
    private MotoRepository motoRepository;

    // ✅ Listar todos os pátios
    public OperationResult<Object> listarTodos() {
        try {
            List<Patio> patios = repository.findAll();
            if (patios.isEmpty()) {
                return OperationResult.failure("Nenhum pátio cadastrado", 204);
            }
            return OperationResult.success(patios);
        } catch (Exception e) {
            return OperationResult.failure("Erro ao listar pátios: " + e.getMessage(), 500);
        }
    }

    public OperationResult<Object> obterPorId(Long id) {
        try {
            Optional<Patio> byId = repository.findById(id);
            if (byId.isEmpty()) return OperationResult.failure("Nenhum pátio encontrado", 204);
            return OperationResult.success(byId.get());
        } catch (Exception e) {
            return OperationResult.failure("Erro ao buscar pátio: " + e.getMessage(), 500);
        }
    }

    public OperationResult<Object> salvar(PatioDTO patioDTO) {
        try {
            Endereco endereco = new Endereco(patioDTO.getEndereco());
            enderecoRepository.save(endereco);

            Patio novoPatio = new Patio(endereco, patioDTO.getImagemPlantaUrl());
            Patio salvo = repository.save(novoPatio);

            return OperationResult.success(salvo, 201);
        } catch (Exception e) {
            return OperationResult.failure("Erro ao salvar pátio: " + e.getMessage(), 500);
        }
    }

    public OperationResult<Object> salvarMoto(Long idPatio, Long idMoto) {
        try {
            if (!repository.existsById(idPatio) || !motoRepository.existsById(idMoto)) {
                return OperationResult.failure("Moto ou pátio não encontrado", 404);
            }

            Patio patio = repository.findById(idPatio).orElse(null);
            Moto moto = motoRepository.findById(idMoto).orElse(null);

            if (patio == null || moto == null) {
                return OperationResult.failure("Moto ou pátio não encontrado", 404);
            }

            // Verifica se a moto já está associada a algum pátio
            Optional<Moto> m = motoRepository.findByPatio(patio);
            if (m.isPresent()) return OperationResult.failure("Moto já cadastrada neste pátio");

            patio.addMoto(moto);
            motoRepository.saveAndFlush(moto);
            Patio atualizado = repository.saveAndFlush(patio);

            return OperationResult.success(atualizado, 201);

        } catch (Exception e) {
            return OperationResult.failure("Erro ao salvar moto no pátio: " + e.getMessage(), 500);
        }
    }

    public OperationResult<Object> editar(Long id, PatioDTO patioDTO) {
        try {
            Patio existente = repository.findById(id).orElse(null);
            if (existente == null) {
                return OperationResult.failure("Pátio não encontrado", 404);
            }

            Patio atualizado = new Patio(new Endereco(patioDTO.getEndereco()), patioDTO.getImagemPlantaUrl());
            atualizado.setId(id);
            atualizado.setMotos(existente.getMotos());

            enderecoRepository.saveAndFlush(atualizado.getEndereco());
            Patio salvo = repository.saveAndFlush(atualizado);

            return OperationResult.success(salvo);
        } catch (Exception e) {
            return OperationResult.failure("Erro ao editar pátio: " + e.getMessage(), 500);
        }
    }

    public OperationResult<Object> deletar(Long id) {
        try {
            if (!repository.existsById(id)) {
                return OperationResult.failure("Pátio não encontrado", 404);
            }

            repository.deleteById(id);
            return OperationResult.success("Pátio deletado com sucesso!");
        } catch (Exception e) {
            return OperationResult.failure("Erro ao deletar pátio: " + e.getMessage(), 500);
        }
    }

    public OperationResult<Object> salvarUsuario(User user, Long idPatio) {
        try {
            Patio patio = repository.findById(idPatio).orElse(null);
            if (patio == null) {
                return OperationResult.failure("Pátio não encontrado", 404);
            }

            patio.addUser(user);
            user.setPatio(patio);
            repository.save(patio);

            return OperationResult.success(user, 201);
        } catch (Exception e) {
            return OperationResult.failure("Erro ao salvar usuário no pátio: " + e.getMessage(), 500);
        }
    }
}
