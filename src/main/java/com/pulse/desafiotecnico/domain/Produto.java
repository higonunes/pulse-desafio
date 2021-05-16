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
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode
public class Produto implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotEmpty(message = "Obrigatório um nome para o produto")
    private String nome;

    @NotNull(message = "Obrigatório o preço do produto")
    private Double preco;

    @NotNull(message = "Obrigatório informar a quantidade em estoque")
    private Integer quantidadeEstoque;


    @JsonIgnore
    @OneToMany(mappedBy = "produto")
    private List<CarrinhoProduto> carrinhoProdutos = new ArrayList<>();

    @JsonIgnore
    @OneToMany(mappedBy = "produto")
    private List<PedidoProduto> PedidoProduto = new ArrayList<>();
}
