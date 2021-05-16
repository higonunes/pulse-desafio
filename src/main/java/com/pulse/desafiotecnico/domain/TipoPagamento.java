package com.pulse.desafiotecnico.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.pulse.desafiotecnico.enums.FormaPagamento;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode
public class TipoPagamento implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private FormaPagamento formaPagamento;

    private Double valorTotal;

    @OneToOne
    @JoinColumn(name = "cartao_id")
    private Cartao cartao;

    @OneToOne
    @JoinColumn(name = "boleto_id")
    private Boleto boleto;

    @JsonIgnore
    @OneToOne(mappedBy = "tipoPagamento")
    private Pagamento pagamento;
}
