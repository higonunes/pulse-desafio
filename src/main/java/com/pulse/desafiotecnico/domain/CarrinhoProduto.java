package com.pulse.desafiotecnico.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Entity
@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode
public class CarrinhoProduto implements Serializable {

    @JsonIgnore
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    private Integer quantidade;
    @ManyToOne
    @JoinColumn(name = "produto_id")
    private Produto produto;
    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "carrinho_id")
    private Carrinho carrinho;

    public boolean isForaDeEstoque() {
        return quantidade > produto.getQuantidadeEstoque();
    }

}
