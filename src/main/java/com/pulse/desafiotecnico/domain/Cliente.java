package com.pulse.desafiotecnico.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.pulse.desafiotecnico.enums.Perfil;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Entity
@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode
public class Cliente implements Serializable {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotEmpty(message = "Nome é obrigatório")
    @Length(min = 3, max = 80, message = "O tamanho deve ser entre 3 e 80 caracteres")
    private String nome;

    @NotEmpty(message = "Email é obrigatório")
    @Column(unique = true)
    @Email(message = "O email informado não é válido")
    private String email;

    @NotNull(message = "CPF obrigatório")
    @Column(unique = true)
    private Long cpf;

    @JsonIgnore
    private String hashSenha;

    @JsonIgnore
    @OneToMany(mappedBy = "cliente")
    private List<Pedido> pedidos = new ArrayList<>();

    @JsonIgnore
    @OneToMany(mappedBy = "cliente")
    private List<Endereco> enderecos;

    @JsonIgnore
    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(name = "PERFIS")
    private final Set<Integer> perfis = new HashSet<>();

    @JsonIgnore
    public Set<Perfil> getPerfis() {
        return perfis.stream().map(Perfil::toEnum).collect(Collectors.toSet());
    }

    public void addPerfil(Perfil perfil) {
        perfis.add(perfil.getId());
    }
}
