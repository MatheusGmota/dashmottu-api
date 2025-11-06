package br.com.dashmottu.model.dto;

import br.com.dashmottu.model.enums.ModeloMoto;
import br.com.dashmottu.model.enums.StatusMoto;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MotoResponseDTO {

    private Long idPatio;
    private Long id;
    private String codTag;
    private ModeloMoto modelo;
    private String placa;
    private StatusMoto status;

    public MotoResponseDTO(Long id, String codTag, ModeloMoto modelo, String placa, StatusMoto status) {
        this.id = id;
        this.codTag = codTag;
        this.modelo = modelo;
        this.placa = placa;
        this.status = status;
    }
    public MotoResponseDTO(Long idPatio, Long id, String codTag, ModeloMoto modelo, String placa, StatusMoto status) {
        this.idPatio = idPatio;
        this.id = id;
        this.codTag = codTag;
        this.modelo = modelo;
        this.placa = placa;
        this.status = status;
    }
}
