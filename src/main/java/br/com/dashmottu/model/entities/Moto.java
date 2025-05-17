package br.com.dashmottu.model.entities;

import br.com.dashmottu.model.enums.ModeloMoto;
import br.com.dashmottu.model.enums.StatusMoto;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

@Entity
@Table(name = "TB_MOTO")
@SequenceGenerator(name = "moto", sequenceName = "SQ_TB_MOTO",allocationSize = 1)
public class Moto {

    @Id
    @Column(name = "id_moto")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "moto")
    private Long id;

    @Column(name = "cod_tag", nullable = false, length = 50, unique = true)
    private String codTag;

    @Enumerated(EnumType.STRING)
    @Column(name = "modelo", nullable = false)
    private ModeloMoto modelo;

    @Column(name = "placa", nullable = false, length = 8)
    private String placa;

    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false)
    private StatusMoto status;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "id_patio")
    private Patio patio;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "id_loc")
    private Localizacao localizacao;

    public Moto() {};

    public Moto(String codTag, ModeloMoto modelo, String placa, StatusMoto status) {
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

    public Patio getPatio() {
        return patio;
    }

    public void setPatio(Patio patio) {
        this.patio = patio;
    }

    public Localizacao getLocalizacao() {
        return localizacao;
    }

    public void setLocalizacao(Localizacao localizacao) {
        this.localizacao = localizacao;
    }
}
