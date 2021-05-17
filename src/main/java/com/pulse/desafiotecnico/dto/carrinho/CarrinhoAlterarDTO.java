package com.pulse.desafiotecnico.dto.carrinho;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.validation.constraints.AssertTrue;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@ToString
public class CarrinhoAlterarDTO implements Serializable {

    private Long idCarrinho;

    private List<CarrinhoProdutoDTO> carrinhoProduto = new ArrayList<>();

    private String cupom;

    private Long idEndereco;

    private Long idTransportadora;

    private Double valorFrete;

    public Double getValorFrete() {
        return valorFrete == null ? 0.0 : valorFrete;
    }

    @AssertTrue(message = "É obrigatório informar o valor do frete para alterar a transportadora ou endereço")
    public boolean isValorFrete() {
        if (idTransportadora != null && valorFrete == null) {
            return false;
        } else return idEndereco == null || valorFrete != null;
    }

}



