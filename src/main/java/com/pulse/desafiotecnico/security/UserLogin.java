package com.pulse.desafiotecnico.security;

import com.pulse.desafiotecnico.enums.Perfil;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Set;
import java.util.stream.Collectors;

@NoArgsConstructor
@ToString
public class UserLogin implements UserDetails {

    /**
     *
     */
    private static final long serialVersionUID = 1L;

    private Long id;
    private String email;
    private String senha;
    private String nome;
    private Long cpf;
    private Collection<? extends GrantedAuthority> authorities;

    public UserLogin(Long id, String nome, String email, String senha, Long cpf, Set<Perfil> perfis) {
        this.id = id;
        this.nome = nome;
        this.email = email;
        this.senha = senha;
        this.cpf = cpf;
        this.authorities = perfis.stream().map(x -> new
                SimpleGrantedAuthority(x.getDescricao())).collect(Collectors.toList());
    }

    public Long getId() {
        return this.id;
    }

    public String getNome() {
        return this.nome;
    }

    public Long getCpf() {
        return this.cpf;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public String getPassword() {
        return this.senha;
    }

    @Override
    public String getUsername() {
        return this.email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
