package com.algaworks.algafoodapi.domain.model;

import lombok.*;

import javax.persistence.*;
import java.math.BigDecimal;

@Table(name = "tab_item_pedido")
@Entity
@Getter
@Setter
@ToString
@NoArgsConstructor
public class ItemPedido {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "item_quantidade")
    private Integer quantidade;

    @Column(name = "item_preco_unitario")
    private BigDecimal precoUnitario;

    @Column(name = "item_preco_total")
    private BigDecimal precoTotal;

    @Column(name = "item_observacao")
    private String observacao;

    @ManyToOne
    @JoinColumn(nullable = false)
    private Pedido pedido;

    @ManyToOne
    @JoinColumn(nullable = false)
    private Produto produto;
}
