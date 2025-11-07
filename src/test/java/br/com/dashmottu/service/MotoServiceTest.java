package br.com.dashmottu.service;

import br.com.dashmottu.model.entities.Moto;
import br.com.dashmottu.model.OperationResult;
import br.com.dashmottu.model.dto.MotoRequestDTO;
import br.com.dashmottu.model.dto.MotoResponseDTO;
import br.com.dashmottu.model.enums.ModeloMoto;
import br.com.dashmottu.model.enums.StatusMoto;
import br.com.dashmottu.repository.MotoRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class MotoServiceTest {

    @Mock
    private MotoRepository repository;

    @InjectMocks
    private MotoService motoService;

    @Test
    void deveSalvarMotoComSucesso() {
        // Arrange
        MotoRequestDTO dto = new MotoRequestDTO("TAG123", ModeloMoto.MOTO_E, "XYZ1239", StatusMoto.PENDENCIA);
        Moto moto = new Moto("TAG123", ModeloMoto.MOTO_E, "XYZ9999", StatusMoto.PENDENCIA);
        moto.setId(1L);

        when(repository.save(any(Moto.class))).thenReturn(moto);

        // Act
        OperationResult<Object> result = motoService.salvar(dto);

        // Assert
        assertNotNull(result);
        assertEquals(201, result.getStatusCode());
        assertNull(result.getErrorMessage());
        assertInstanceOf(MotoResponseDTO.class, result.getData());

        MotoResponseDTO response = (MotoResponseDTO) result.getData();
        assertEquals("TAG123", response.getCodTag());
        assertEquals(ModeloMoto.MOTO_E, response.getModelo());
        assertEquals("XYZ1239", response.getPlaca());
        assertEquals(StatusMoto.PENDENCIA, response.getStatus());

        verify(repository).save(any(Moto.class));
    }

    @Test
    void deveRetornarErroQuandoMotoDuplicada() {
        // Arrange
        MotoRequestDTO dto = new MotoRequestDTO("TAG999", ModeloMoto.MOTO_E, "XYZ9999", StatusMoto.PENDENCIA);
        when(repository.save(any(Moto.class)))
                .thenThrow(new RuntimeException("ORA-00001: unique constraint violated"));

        // Act
        OperationResult<Object> result = motoService.salvar(dto);

        // Assert
        assertNotNull(result);
        assertEquals(400, result.getStatusCode());
        assertEquals("Moto já cadastrada", result.getErrorMessage());
        assertNull(result.getData());

        verify(repository).save(any(Moto.class));
    }

    @Test
    void deveRetornarErroQuandoCamposVazios() {
        // Arrange
        MotoRequestDTO dto = new MotoRequestDTO("", null, "", null);
        when(repository.save(any(Moto.class)))
                .thenThrow(new RuntimeException("Campo obrigatório não preenchido"));

        // Act
        OperationResult<Object> result = motoService.salvar(dto);

        // Assert
        assertNotNull(result);
        assertEquals(500, result.getStatusCode());
        assertTrue(result.getErrorMessage().contains("Erro ao salvar moto"));
        assertNull(result.getData());

        verify(repository).save(any(Moto.class));
    }
}
