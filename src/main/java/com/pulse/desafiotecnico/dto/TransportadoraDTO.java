package com.pulse.desafiotecnico.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
@ToString
public class TransportadoraDTO implements Serializable {

    private Long id;

    @NotEmpty(message = "O nome é obrigatório")
    private String nome;

    @NotNull(message = "O CEP é obrigatório")
    private Integer cep;

    private Double valorFrete;


}
