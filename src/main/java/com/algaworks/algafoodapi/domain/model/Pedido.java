package com.algaworks.algafoodapi.domain.model;

import com.algaworks.algafoodapi.domain.enums.StatusPedido;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.sun.istack.NotNull;
import lombok.*;
import org.hibernate.Hibernate;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Getter
@Setter
@ToString
@Entity
@Table(name = "tab_pedido")
@NoArgsConstructor
public class Pedido {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(name = "ped_sub_total")
    private BigDecimal subTotal;

    @NotNull
    @Column(name = "ped_taxa_frete")
    private BigDecimal taxaFrete;

    @NotNull
    @Column(name = "ped_valor_total")
    private BigDecimal valorTotal;

    @ManyToOne
    @JoinColumn(name = "ped_forma_pagamento_id", nullable = false)
    private FormaPagamento formaPagamento;

    @ManyToOne
    @JoinColumn(name = "ped_restaurante_id", nullable = false)
    private Restaurante restaurante;

    @ManyToOne
    @JoinColumn(name = "ped_usuario_id", nullable = false)
    private Usuario usuario;

    @Column(name = "ped_status_pedido")
    private StatusPedido statusPedido;

    @Embedded
    @JsonIgnore
    private Endereco endereco;

    @OneToMany
    @ToString.Exclude
    private List<ItemPedido> itens = new ArrayList<>();

    @Column(name = "ped_data_criacao", nullable = false, columnDefinition = "datetime")
    private LocalDateTime dataCriacao;

    @Column(name = "ped_data_confirmacao", columnDefinition = "datetime")
    private LocalDateTime dataConfirmacao;

    @Column(name = "ped_data_cancelamento", columnDefinition = "datetime")
    private LocalDateTime dataCancelamento;

    @Column(name = "ped_data_entrega", columnDefinition = "datetime")
    private LocalDateTime dataEntrega;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        Pedido pedido = (Pedido) o;
        return id != null && Objects.equals(id, pedido.id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }

}
