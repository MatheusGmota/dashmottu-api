package br.com.dashmottu.model.dto;

import br.com.dashmottu.model.enums.ModeloMoto;
import br.com.dashmottu.model.enums.StatusMoto;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class MotoRequestDTO {;

    private Long id;

    @NotBlank(message = "O código da tag é obrigatório")
    @Size(min = 6, max = 10, message = "O código da tag deve ter entre 6 a 10 caracteres")
    private String codTag;

    @NotNull
    private ModeloMoto modelo;

    @NotBlank(message = "A placa é obrigatório")
    @Size(min = 7, max = 7, message = "A placa deve ter 7 caracteres")
    private String placa;

    @NotNull
    private StatusMoto status;

    public MotoRequestDTO() {
    }

    public MotoRequestDTO(String codTag, ModeloMoto modelo, String placa, StatusMoto status) {
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
