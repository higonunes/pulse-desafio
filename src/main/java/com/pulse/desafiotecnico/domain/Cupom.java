package com.pulse.desafiotecnico.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.pulse.desafiotecnico.enums.TipoCupom;
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
public class Cupom implements Serializable {

    @JsonIgnore
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @JsonIgnore
    private TipoCupom tipoCupom;

    private String nomeCupom;

    @JsonIgnore
    private Double fator;

    @JsonIgnore
    @OneToMany(mappedBy = "cupom")
    private List<Carrinho> carrinho;

    public String getDesconto() {
        if (tipoCupom.equals(TipoCupom.PORCENTAGEM)) {
            return (int) (fator * 100) + "% de desconto";
        } else {
            return fator + " reais de desconto";
        }
    }

}
