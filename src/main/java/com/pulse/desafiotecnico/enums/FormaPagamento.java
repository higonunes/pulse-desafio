package com.pulse.desafiotecnico.enums;

public enum FormaPagamento {
    CARTAO(1, "CARTAO"),
    BOLETO(2, "BOLETO");

    private final int id;
    private final String descricao;

    FormaPagamento(int id, String descricao) {
        this.id = id;
        this.descricao = descricao;
    }

    public static Perfil toEnum(Integer id) {
        if (id == null) return null;

        for (Perfil x : Perfil.values()) {
            if (id.equals(x.getId())) return x;
        }

        throw new IllegalArgumentException("ID da forma de pagamento inválida");
    }

    public int getId() {
        return id;
    }

    public String getDescricao() {
        return descricao;
    }
}
