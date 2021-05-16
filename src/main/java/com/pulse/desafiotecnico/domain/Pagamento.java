package com.pulse.desafiotecnico.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
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
public class Pagamento implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @JsonFormat(pattern = "dd/MM/yyyy HH:mm")
    private Date data;

    @JsonIgnore
    @OneToMany(mappedBy = "pagamento")
    private List<Pedido> pedido;

    @OneToOne
    @JoinColumn(name = "tipoPagamento_id")
    private TipoPagamento tipoPagamento;

    @PrePersist
    private void setDataPagamento() {
        this.data = new Date();
    }
}
