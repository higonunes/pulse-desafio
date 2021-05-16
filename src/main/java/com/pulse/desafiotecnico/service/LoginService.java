package com.pulse.desafiotecnico.service;

import com.pulse.desafiotecnico.security.UserLogin;
import org.springframework.security.core.context.SecurityContextHolder;

public class LoginService {

    public static UserLogin autenticado() {
        try {
            return (UserLogin) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        } catch (Exception e) {
            return null;
        }
    }
}
