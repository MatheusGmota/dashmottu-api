package br.com.dashmottu.model.entities;

import br.com.dashmottu.model.dto.UserDto;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@Table(name = "tb_patio")
@Getter
@Setter
@NoArgsConstructor
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

    @JsonIgnore
    @OneToMany(mappedBy = "patio", cascade = CascadeType.ALL)
    private List<Moto> motos;

    @JsonIgnore
    @OneToMany(mappedBy = "patio", cascade = CascadeType.ALL)
    private List<User> users;

    public Patio(Endereco endereco, String imagemPlantaUrl) {
        this.endereco = endereco;
        this.imagemPlantaUrl = imagemPlantaUrl;
    }

    public void addMoto(Moto moto) {
        moto.setPatio(this);
        this.motos.add(moto);
    }

    public void addUser(User user) {
        user.setPatio(this);
        this.users.add(user);
    }

    @JsonIgnore
    public List<UserDto> getUsersDto() {
        return users.stream().map(user -> new UserDto(user.getId(), user.getLogin(), user.getRole(), true)).toList();
    }

}
