package com.pulse.desafiotecnico.dto;

import com.pulse.desafiotecnico.enums.TipoCupom;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.validation.constraints.AssertTrue;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
@ToString
public class CupomNovoDTO implements Serializable {

    @NotNull(message = "O tipo de cupom deve ser informado")
    private TipoCupom tipoCupom;

    @NotEmpty(message = "O nome do cupom deve ser informado")
    private String nomeCupom;

    @NotNull(message = "O fator de desconto do cupom deve ser informado")
    private Double fator;

    @AssertTrue(message = "Cupons do tipo porcentagem devem ter o fator entre 0 e 1")
    public boolean isFatorValido() {
        if(this.tipoCupom != null && this.tipoCupom.getId() == TipoCupom.PORCENTAGEM.getId() && this.fator > 1) {
            return false;
        } else {
            return true;
        }
    }
}
