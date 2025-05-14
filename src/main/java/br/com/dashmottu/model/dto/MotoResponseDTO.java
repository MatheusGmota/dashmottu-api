package br.com.dashmottu.model.dto;

import br.com.dashmottu.model.enums.ModeloMoto;
import br.com.dashmottu.model.enums.StatusMoto;

public class MotoResponseDTO {
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
