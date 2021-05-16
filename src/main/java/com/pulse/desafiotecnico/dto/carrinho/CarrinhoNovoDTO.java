package com.pulse.desafiotecnico.dto.carrinho;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.validation.constraints.AssertTrue;
import javax.validation.constraints.NotEmpty;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@ToString
public class CarrinhoNovoDTO implements Serializable {

    @NotEmpty(message = "É necessário adicionar ao menos um produto para criar o carrinho")
    private List<CarrinhoProdutoDTO> carrinhoProduto = new ArrayList<>();

}
