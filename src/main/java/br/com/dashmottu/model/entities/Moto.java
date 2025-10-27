package br.com.dashmottu.model.entities;

import br.com.dashmottu.model.enums.ModeloMoto;
import br.com.dashmottu.model.enums.StatusMoto;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "TB_MOTO")
@Getter
@Setter
@NoArgsConstructor
@SequenceGenerator(name = "moto", sequenceName = "SQ_TB_MOTO", allocationSize = 1)
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

    @Column(name = "placa", nullable = false, length = 8, unique = true)
    private String placa;

    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false)
    private StatusMoto status;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "id_patio", unique = true)
    private Patio patio;

    public Moto(String codTag, ModeloMoto modelo, String placa, StatusMoto status) {
        this.codTag = codTag;
        this.modelo = modelo;
        this.placa = placa;
        this.status = status;
    }
}
