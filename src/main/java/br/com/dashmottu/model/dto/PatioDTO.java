package br.com.dashmottu.model.dto;

import br.com.dashmottu.model.entities.Moto;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

import java.util.List;

public class PatioDTO {

    private Long id;

    @Valid
    private EnderecoDTO endereco;

    @NotBlank(message = "A imagem é obrigatória")
    @Size(min=3, max=100, message = "A url da imagem tem que ter entre 3 a 100 caracteres")
    private String imagemPlantaUrl;

    private List<Moto> motos;

    public PatioDTO() {
    }

    public PatioDTO(EnderecoDTO endereco, String imagemPlantaUrl, List<Moto> motos) {
        this.endereco = endereco;
        this.imagemPlantaUrl = imagemPlantaUrl;
        this.motos = motos;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public EnderecoDTO getEndereco() {
        return endereco;
    }

    public void setEndereco(EnderecoDTO endereco) {
        this.endereco = endereco;
    }

    public String getImagemPlantaUrl() {
        return imagemPlantaUrl;
    }

    public void setImagemPlantaUrl(String imagemPlantaUrl) {
        this.imagemPlantaUrl = imagemPlantaUrl;
    }

    public List<Moto> getMotos() {
        return motos;
    }

    public void setMotos(List<Moto> motos) {
        this.motos = motos;
    }
}
