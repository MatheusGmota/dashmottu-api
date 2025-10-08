package br.com.dashmottu.model.entities;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@Document(collection = "localizacoes")
public class Localizacao {

    @Id
    private String id;

    private String codTag;
    private Double posicaoX;
    private Double posicaoY;
    private Date ultimaModificacao;

    public Localizacao(String codTag, Double posicaoX, Double posicaoY) {
        this.codTag = codTag;
        this.posicaoX = posicaoX;
        this.posicaoY = posicaoY;
        this.ultimaModificacao = new Date();
    }
}
