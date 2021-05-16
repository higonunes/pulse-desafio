package com.pulse.desafiotecnico.domain;

import com.pulse.desafiotecnico.enums.TipoCupom;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode
public class Carrinho implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "carrinho")
    private List<CarrinhoProduto> carrinhoProdutos = new ArrayList<>();

    @ManyToOne
    @JoinColumn(name = "cupom_id")
    private Cupom cupom;

    @ManyToOne
    @JoinColumn(name = "transportadora_id")
    private Transportadora transportadora;

    @ManyToOne
    @JoinColumn(name = "endereco_id")
    private Endereco enderecoEntrega;

    private Double valorFrete;

    public Double getValorBruto() {
        return carrinhoProdutos.stream().map(x -> x.getQuantidade() * x.getProduto().getPreco()).reduce(0.0, (a, b) -> a + b);
    }

    public Double getValorTotal() {
        return this.getValorBruto() + this.getValorFrete() - this.getValorDesconto();
    }

    public Double getValorFrete() {
        return valorFrete == null ? 0.0: valorFrete;
    }

    public Double getValorDesconto() {
        if (cupom == null) return 0.0;

        if (cupom.getTipoCupom().getId() == TipoCupom.PORCENTAGEM.getId()) {
            return this.getValorBruto() * cupom.getFator();
        } else if (cupom.getTipoCupom().getId() == TipoCupom.SUBTRACAO.getId()) {
            return cupom.getFator();
        } else {
            return 0.0;
        }
    }

}
