package com.pulse.desafiotecnico.enums;

public enum EstadoPedido {
    ABERTO(1, "ABERTO"),
    AGUARDANDOPAGAMENTO(2, "AGUARDANDO PAGAMENTO"),
    PAGO(3, "PAGO"),
    ENVIADO(4, "ENVIADO"),
    ENTREGUE(5, "ENTREGUE");

    private final int id;
    private final String descricao;

    EstadoPedido(int id, String descricao) {
        this.id = id;
        this.descricao = descricao;
    }

    public static Perfil toEnum(Integer id) {
        if (id == null) return null;

        for (Perfil x : Perfil.values()) {
            if (id.equals(x.getId())) return x;
        }

        throw new IllegalArgumentException("ID estado de pedido inválido");
    }

    public int getId() {
        return id;
    }

    public String getDescricao() {
        return descricao;
    }
}
