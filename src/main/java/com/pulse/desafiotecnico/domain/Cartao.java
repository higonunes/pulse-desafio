package com.pulse.desafiotecnico.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Date;

@Entity
@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode
public class Cartao implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotEmpty(message = "Insira o número do cartão")
    private String numero;

    @NotNull(message = "Insira a data de vencimento")
    @JsonFormat(pattern = "MM/yyyy")
    private Date vencimento;

    @NotNull(message = "Insira o código de verificação do cartão")
    private Integer cvv;

    @JsonIgnore
    @OneToOne(mappedBy = "cartao")
    private TipoPagamento tipoPagamento;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "cliente_id")
    private Cliente cliente;

}
