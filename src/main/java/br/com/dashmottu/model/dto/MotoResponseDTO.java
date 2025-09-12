package br.com.dashmottu.model.dto;

import br.com.dashmottu.model.entities.Moto;
import br.com.dashmottu.model.enums.ModeloMoto;
import br.com.dashmottu.model.enums.StatusMoto;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
public class MotoResponseDTO {
    private Long id;
    private String codTag;
    private ModeloMoto modelo;
    private String placa;
    private StatusMoto status;

    public MotoResponseDTO(Moto moto) {
        this.id = moto.getId();
        this.codTag = moto.getCodTag();
        this.modelo = moto.getModelo();
        this.placa = moto.getPlaca();
        this.status = moto.getStatus();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCodTag() {
        return codTag;
    }

    public void setCodTag(String codTag) {
        this.codTag = codTag;
    }

    public ModeloMoto getModelo() {
        return modelo;
    }

    public void setModelo(ModeloMoto modelo) {
        this.modelo = modelo;
    }

    public String getPlaca() {
        return placa;
    }

    public void setPlaca(String placa) {
        this.placa = placa;
    }

    public StatusMoto getStatus() {
        return status;
    }

    public void setStatus(StatusMoto status) {
        this.status = status;
    }
}
