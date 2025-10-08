package br.com.dashmottu.model.entities;

import br.com.dashmottu.model.dto.EnderecoDTO;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "TB_ENDERECO")
@SequenceGenerator(name = "endereco", sequenceName = "SQ_TB_ENDERECO", allocationSize = 1)
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Endereco {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "endereco")
    @Column(name = "id_endereco")
    private Long id;

    @Column(name = "logradouro", nullable = false, length = 100)
    private String logradouro;

    @Column(name = "numero", nullable = false)
    private Integer numero;

    @Column(name = "bairro", nullable = false, length = 50)
    private String bairro;

    @Column(name = "cidade", nullable = false, length = 50)
    private String cidade;

    @Column(name = "estado", nullable = false, length = 2)
    private String estado;

    @Column(name = "cep", nullable = false, length = 9)
    private String cep;

    public Endereco(EnderecoDTO dto) {
        this.logradouro = dto.getLogradouro();
        this.numero = dto.getNumero();
        this.bairro = dto.getBairro();
        this.cidade = dto.getCidade();
        this.estado = dto.getEstado();
        this.cep = dto.getCep();
    }
}
