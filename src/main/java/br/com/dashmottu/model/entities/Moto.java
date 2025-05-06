package br.com.dashmottu.model.entities;

import br.com.dashmottu.model.enums.ModeloMoto;
import br.com.dashmottu.model.enums.StatusMoto;
import jakarta.persistence.*;

import java.util.Date;

@Entity
@Table(name = "TB_MOTO")
@SequenceGenerator(name = "moto", sequenceName = "SQ_TB_MOTO",allocationSize = 1)
public class Moto {

    @Id
    @Column(name = "id_moto")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "moto")
    private Long id;

    @Column(name = "cod_tag", nullable = false, length = 50)
    private String codTag;

    @Enumerated(EnumType.STRING)
    @Column(name = "modelo", nullable = false)
    private ModeloMoto modelo;

    @Column(name = "placa", nullable = false, length = 8)
    private String placa;

    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false)
    private StatusMoto status;

    // atributo com localizacao (classe externa)

    @Temporal(TemporalType.TIMESTAMP)
    private Date ultimaModificacao;

    public Moto() {};

    public Moto(Date ultimaModificacao, String placa, StatusMoto status, ModeloMoto modelo) {
        this.ultimaModificacao = ultimaModificacao;
        this.placa = placa;
        this.status = status;
        this.modelo = modelo;
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

    public Date getUltimaModificacao() {
        return ultimaModificacao;
    }

    public void setUltimaModificacao(Date ultimaModificacao) {
        this.ultimaModificacao = ultimaModificacao;
    }
}
