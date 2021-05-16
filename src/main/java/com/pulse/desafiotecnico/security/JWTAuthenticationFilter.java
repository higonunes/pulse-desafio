package com.pulse.desafiotecnico.security;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.pulse.desafiotecnico.dto.LoginDTO;
import org.json.JSONObject;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.stream.Collectors;

public class JWTAuthenticationFilter extends UsernamePasswordAuthenticationFilter {

    private final AuthenticationManager authenticationManager;
    private final JWTUtil jwtUtil;

    public JWTAuthenticationFilter(AuthenticationManager authenticationManager, JWTUtil jwtUtil) {
        this.authenticationManager = authenticationManager;
        this.jwtUtil = jwtUtil;
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {
        try {
            LoginDTO credenciais = new ObjectMapper().readValue(request.getInputStream(), LoginDTO.class);
            Authentication token = new UsernamePasswordAuthenticationToken(credenciais.getEmail(), credenciais.getSenha(), new ArrayList<>());
            return authenticationManager.authenticate(token);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain, Authentication authResult) throws IOException, ServletException {
        UserLogin user = ((UserLogin) authResult.getPrincipal());
        String token = jwtUtil.generateToken(user.getUsername());

        response.setContentType(MediaType.APPLICATION_JSON_VALUE);
        response.setCharacterEncoding("UTF-8");

        JSONObject obj = new JSONObject();
        obj.put("id", user.getId());
        obj.put("nome", user.getNome());
        obj.put("email", user.getUsername());
        obj.put("cpf", user.getCpf());
        obj.put("perfis", user.getAuthorities().stream().map(x -> x.toString().substring(5)).collect(Collectors.toList()));

        JSONObject obj2 = new JSONObject();
        obj2.put("token", "Bearer " + token);
        obj2.put("user", obj);
        response.getWriter().write(obj2.toString());
    }

    @Override
    protected void unsuccessfulAuthentication(HttpServletRequest request, HttpServletResponse response, AuthenticationException failed) throws IOException, ServletException {
        response.setStatus(401);
    }
}
