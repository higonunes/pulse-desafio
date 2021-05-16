package com.pulse.desafiotecnico.enums;

public enum TipoCupom {
    PORCENTAGEM(1, "PORCENTAGEM"),
    SUBTRACAO(2, "SUBTRACAO");

    private final int id;
    private final String descricao;

    TipoCupom(int id, String descricao) {
        this.id = id;
        this.descricao = descricao;
    }

    public static Perfil toEnum(Integer id) {
        if (id == null) return null;

        for (Perfil x : Perfil.values()) {
            if (id.equals(x.getId())) return x;
        }

        throw new IllegalArgumentException("ID tipo de cupom inválido");
    }

    public int getId() {
        return id;
    }

    public String getDescricao() {
        return descricao;
    }
}
