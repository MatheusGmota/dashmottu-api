package br.com.dashmottu.model.dto;

import br.com.dashmottu.model.entities.Moto;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class PatioDTO {

    private Long id;

    @Valid
    private EnderecoDTO endereco;

    @NotBlank(message = "A imagem é obrigatória")
    @Size(min=3, max=100, message = "A url da imagem tem que ter entre 3 a 100 caracteres")
    private String imagemPlantaUrl;

    private List<Moto> motos;
}
