package br.com.dashmottu.model.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;

import java.util.Date;

@Entity
@Table(name = "tb_localizacao")
@SequenceGenerator(name = "localizacao", sequenceName = "SQ_TB_LOCALIZACAO",allocationSize = 1)
public class Localizacao {

    @Id
    @Column(name = "id_loc")
    @GeneratedValue(generator = "localizacao", strategy = GenerationType.SEQUENCE)
    private Long id;

    @Column(name = "posicao_x", precision = 6)
    private Double posicaoX;

    @Column(name = "posicao_y", precision = 6)
    private Double posicaoY;

    @Temporal(TemporalType.TIMESTAMP)
    private Date ultimaModificacao;

    @JsonIgnore
    @OneToOne(mappedBy = "localizacao")
    private Moto moto;

    public Localizacao() {};

    public Localizacao(Double posicaoy, Double posicaoX) {
        this.posicaoY = posicaoy;
        this.posicaoX = posicaoX;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public Date getUltimaModificacao() {
        return ultimaModificacao;
    }

    public void setUltimaModificacao(Date ultimaModificacao) {
        this.ultimaModificacao = ultimaModificacao;
    }

    public Moto getMoto() {
        return moto;
    }

    public void setMoto(Moto moto) {
        this.moto = moto;
    }
}