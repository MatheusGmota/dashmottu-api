package br.com.dashmottu.model.dto;

import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

import java.util.Date;

public class LocalizacaoDTO {

    @Min(value = 0, message = "A posição tem que ter no mínimo o valor 0")
    @Max(value = 1000, message = "A posição pode ter no máximo o valor 1000")
    @NotNull(message = "A posicao_x é obrigatória")
    private Double posicaoX;

    @Min(value = 0, message = "A posição tem que ter no mínimo o valor 0")
    @Max(value = 1000, message = "A posição pode ter no máximo o valor 1000")
    @NotNull(message = "A posicao_y é obrigatória")
    private Double posicaoY;

    private Date ultimaModificacao;

    public LocalizacaoDTO(Double posicaoX, Double posicaoY) {
        this.posicaoX = posicaoX;
        this.posicaoY = posicaoY;
    }

    public Double getPosicaoX() {
        return posicaoX;
    }

    public void setPosicaoX(Double posicaoX) {
        this.posicaoX = posicaoX;
    }

    public Double getPosicaoY() {
        return posicaoY;
    }

    public void setPosicaoY(Double posicaoY) {
        this.posicaoY = posicaoY;
    }
}
