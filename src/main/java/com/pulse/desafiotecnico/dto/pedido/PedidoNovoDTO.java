package com.pulse.desafiotecnico.dto.pedido;

import com.pulse.desafiotecnico.enums.FormaPagamento;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.validation.constraints.AssertTrue;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
@ToString
public class PedidoNovoDTO implements Serializable {

    @NotNull(message = "Informe o ID de um carrinho com produtos")
    private Long idCarrinho;

    @NotNull
    private FormaPagamento formaPagamento;

    private Long idCartao;

    @AssertTrue(message = "É obrigatório informar um ID de cartão para o modo de pagamento")
    private boolean isidCartao() {
        if (formaPagamento == null) {
            return false;
        }
        return !(formaPagamento.getId() == FormaPagamento.CARTAO.getId() && idCartao == null);
    }
}
