package com.pulse.desafiotecnico.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.validator.constraints.Length;

import javax.persistence.Column;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
@ToString
public class ClienteNovoDTO implements Serializable {

    @NotEmpty(message = "Nome é obrigatório")
    @Length(min = 3, max = 80, message = "O tamanho deve ser entre 3 e 80 caracteres")
    private String nome;

    @NotEmpty(message = "Email é obrigatório")
    @Column(unique = true)
    @Email(message = "O email informado não é válido")
    private String email;

    @NotNull(message = "CPF obrigatório")
    private Long cpf;

    @NotEmpty(message = "Informe um senha")
    private String senha;
}
