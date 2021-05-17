package com.pulse.desafiotecnico.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode
public class Transportadora implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;

    @JsonIgnore
    private Integer cep;

    @JsonIgnore
    @OneToMany(mappedBy = "transportadora")
    private List<Pedido> pedidos;

    @JsonIgnore
    @OneToMany(mappedBy = "transportadora")
    private List<Carrinho> carrinhos;
}
