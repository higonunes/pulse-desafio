package com.pulse.desafiotecnico.dto.carrinho;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
@ToString
public class CarrinhoProdutoDTO implements Serializable {

    @NotNull
    private Long idProduto;

    @NotNull
    private Integer quantidade;

    @NotNull
    private Long idCarrinho;

}
