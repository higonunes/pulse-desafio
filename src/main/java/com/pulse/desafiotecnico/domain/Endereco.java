package com.pulse.desafiotecnico.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode
public class Endereco implements Serializable {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotEmpty(message = "Nome da rua é obrigatório")
    private String rua;
    private String complemento;

    @NotEmpty(message = "Bairro é obrigatório")
    private String bairro;

    @NotNull(message = "Número é obrigatório")
    private Integer numero;
    private Integer cep;

    @NotEmpty(message = "Estado é obrigatório")
    private String Estado;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "cliente_id")
    private Cliente cliente;

    @JsonIgnore
    @OneToMany(mappedBy = "enderecoEntrega")
    private List<Pedido> pedidos;

    @JsonIgnore
    @OneToMany(mappedBy = "enderecoEntrega")
    private List<Carrinho> carrinhos;
}
