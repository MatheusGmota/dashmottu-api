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
}
