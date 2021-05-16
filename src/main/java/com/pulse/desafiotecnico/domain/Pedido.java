package com.pulse.desafiotecnico.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.pulse.desafiotecnico.enums.EstadoPedido;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode
public class Pedido implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @JsonFormat(pattern = "dd/MM/yyyy HH:mm")
    private Date data;

    private Double valorBruto, valorDesconto, valorFrete;

    private Long numeroPedido;

    private EstadoPedido estadoPedido;

    @ManyToOne
    @JoinColumn(name = "cupom_id")
    private Cupom cupom;

    @ManyToOne
    @JoinColumn(name = "cliente_id")
    private Cliente cliente;

    @OneToMany(mappedBy = "pedido")
    private List<PedidoProduto> produtos;

    @ManyToOne
    @JoinColumn(name = "endereco_id")
    private Endereco enderecoEntrega;

    @ManyToOne
    @JoinColumn(name = "transportadora_id")
    private Transportadora transportadora;

    @ManyToOne
    @JoinColumn(name = "pagamento_id")
    private Pagamento pagamento;

    public Double getValorTotal() {
        return this.valorBruto + this.valorFrete - valorDesconto;
    }

    @PrePersist
    private void setDataPedido() {
        this.data = new Date();
    }

}
