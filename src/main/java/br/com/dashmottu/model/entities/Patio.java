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

    @OneToOne
    @JoinColumn(name="id_endereco")
    private Endereco endereco;

    private String imagemPlantaUrl;

    @OneToMany(mappedBy = "patio")
    private List<Moto> motos;

    public Patio(Endereco endereco, String imagemPlantaUrl) {
        this.endereco = endereco;
        this.imagemPlantaUrl = imagemPlantaUrl;
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
