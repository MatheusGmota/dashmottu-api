package br.com.dashmottu.model.entities;

import jakarta.persistence.*;

import java.util.Date;

@Entity
@Table(name = "tb_localizacao")
public class Localizacao {

    @Id
    @Column(name = "id_loc")
    private Long id;

    @Column(name = "posicao_x", precision = 6)
    private Double posicaoX;

    @Column(name = "posicao_y", precision = 6)
    private Double posicaoy;

    @Temporal(TemporalType.TIMESTAMP)
    private Date ultimaModificacao;

    @OneToOne(mappedBy = "localizacao")
    private Moto moto;

    public Localizacao(Double posicaoy, Double posicaoX) {
        this.posicaoy = posicaoy;
        this.posicaoX = posicaoX;
    }

    public Date getUltimaModificacao() {
        return ultimaModificacao;
    }

    public void setUltimaModificacao(Date ultimaModificacao) {
        this.ultimaModificacao = ultimaModificacao;
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

    public Double getPosicaoy() {
        return posicaoy;
    }

    public void setPosicaoy(Double posicaoy) {
        this.posicaoy = posicaoy;
    }

    public Moto getMoto() {
        return moto;
    }

    public void setMoto(Moto moto) {
        this.moto = moto;
    }
}