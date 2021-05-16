package com.pulse.desafiotecnico.enums;

public enum Perfil {
    ADMIN(1, "ROLE_ADMIN"),
    USUARIO(2, "ROLE_CLIENTE");

    private final int id;
    private final String descricao;

    Perfil(int id, String descricao) {
        this.id = id;
        this.descricao = descricao;
    }

    public static Perfil toEnum(Integer id) {
        if (id == null) return null;

        for (Perfil x : Perfil.values()) {
            if (id.equals(x.id)) return x;
        }

        throw new IllegalArgumentException("ID inválido");
    }

    public int getId() {
        return id;
    }

    public String getDescricao() {
        return descricao;
    }
}