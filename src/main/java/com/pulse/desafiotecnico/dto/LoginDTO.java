package com.pulse.desafiotecnico.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
@ToString
public class LoginDTO implements Serializable {

    private String email, senha;
}
