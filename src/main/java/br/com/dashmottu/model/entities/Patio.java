package br.com.dashmottu.model.entities;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "tb_patio")
@SequenceGenerator(name = "patio", sequenceName = "SQ_TB_PATIO", allocationSize = 1)
public class Patio {

    @Id
    @Column(name = "id_patio")
    @GeneratedValue(generator = "patio", strategy = GenerationType.SEQUENCE)
    private Long id;

    private String imagemPlantaUrl;

    @OneToOne
    @JoinColumn(name="id_endereco", unique = true)
    private Endereco endereco;

    @OneToMany(mappedBy = "patio", cascade = CascadeType.ALL)
    private List<Moto> motos;

    public Patio() {}

    public Patio(Endereco endereco, String imagemPlantaUrl) {
        this.endereco = endereco;
        this.imagemPlantaUrl = imagemPlantaUrl;
    }

    public void addMoto(Moto moto) {
        moto.setPatio(this);
        this.motos.add(moto);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Endereco getEndereco() {
        return endereco;
    }

    public void setEndereco(Endereco endereco) {
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
