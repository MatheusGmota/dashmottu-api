package br.com.dashmottu.service;

import br.com.dashmottu.model.OperationResult;
import br.com.dashmottu.model.dto.MotoRequestDTO;
import br.com.dashmottu.model.dto.MotoResponseDTO;
import br.com.dashmottu.model.entities.Moto;
import br.com.dashmottu.model.entities.Patio;
import br.com.dashmottu.repository.LocalizacaoRepository;
import br.com.dashmottu.repository.MotoRepository;
import br.com.dashmottu.repository.PatioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MotoService {

    @Autowired
    private PatioRepository patioRepository;

    @Autowired
    private MotoRepository repository;

    @Autowired
    private LocalizacaoRepository localizacaoRepository;

    public OperationResult<Object> listarTodos() {
        try {
            List<Moto> motos = repository.findAll();
            if (motos.isEmpty()) {
                return OperationResult.failure("Nenhuma moto cadastrada", 204);
            }
            return OperationResult.success(motos);
        } catch (Exception e) {
            return OperationResult.failure("Erro ao listar motos: " + e.getMessage(), 500);
        }
    }

    public OperationResult<Object> obterPorId(Long id) {
        try {
            Moto moto = repository.findById(id).orElse(null);
            if (moto == null) return OperationResult.failure("Moto não encontrada", 404);
            return OperationResult.success(moto);
        } catch (Exception e) {
            return OperationResult.failure("Erro ao buscar moto: " + e.getMessage(), 500);
        }
    }

    public OperationResult<Object> obterPorTag(String codTag) {
        try {
            Moto moto = repository.findByCodTag(codTag);
            if (moto == null) return OperationResult.failure("Moto não encontrada para o código informado", 404);
            return OperationResult.success(moto);
        } catch (Exception e) {
            return OperationResult.failure("Erro ao buscar moto por tag: " + e.getMessage(), 500);
        }
    }

    public OperationResult<Object> salvar(MotoRequestDTO motoDTO) {
        try {
            Moto moto = new Moto(
                    motoDTO.getCodTag(),
                    motoDTO.getModelo(),
                    motoDTO.getPlaca(),
                    motoDTO.getStatus()
            );

            Moto salvo = repository.save(moto);
            return OperationResult.success(
                    new MotoResponseDTO(moto.getId(),
                    moto.getCodTag(),
                    moto.getModelo(),
                    moto.getPlaca(),
                    moto.getStatus()), 201);
        } catch (Exception e) {
            if (e.getMessage().contains("ORA-00001")) return OperationResult.failure("Moto já cadastrada", 400);
            return OperationResult.failure("Erro ao salvar moto: " + e.getMessage(), 500);
        }
    }

    public OperationResult<Object> editar(Long id, MotoRequestDTO motoRequestDTO) {
        try {
            Moto existente = repository.findById(id).orElse(null);
            if (existente == null) {
                return OperationResult.failure("Moto não encontrada", 404);
            }

            Moto moto = new Moto(
                    motoRequestDTO.getCodTag(),
                    motoRequestDTO.getModelo(),
                    motoRequestDTO.getPlaca(),
                    motoRequestDTO.getStatus()
            );
            moto.setId(id);

            Moto atualizado = repository.saveAndFlush(moto);
            return OperationResult.success(atualizado);
        } catch (Exception e) {
            return OperationResult.failure("Erro ao editar moto: " + e.getMessage(), 500);
        }
    }

    public OperationResult<Object> deletar(Long id) {
        try {
            if (!repository.existsById(id)) {
                return OperationResult.failure("Moto não encontrada", 404);
            }

            repository.deleteById(id);
            return OperationResult.success("Moto deletada com sucesso!");
        } catch (Exception e) {
            return OperationResult.failure("Erro ao deletar moto: " + e.getMessage(), 500);
        }
    }

    public OperationResult<Object> obterMotosPorPatioId(Long idPatio) {
        try {
            Patio patio = patioRepository.findById(idPatio).orElse(null);
            if (patio == null) {
                return OperationResult.failure("Pátio não encontrado", 404);
            }

            List<Moto> motos = patio.getMotos();
            if (motos == null || motos.isEmpty()) {
                return OperationResult.failure("Nenhuma moto vinculada a este pátio", 204);
            }

            return OperationResult.success(motos);
        } catch (Exception e) {
            return OperationResult.failure("Erro ao buscar motos do pátio: " + e.getMessage(), 500);
        }
    }
}
