package com.algaworks.algafoodapi.domain.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.Hibernate;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "tab_restaurante")
@Getter
@Setter
@ToString
@RequiredArgsConstructor
public class Restaurante {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "nom_restaurante")
	private String nome;

	@Column(name = "taxa_frete", nullable = false)
	private BigDecimal taxaFrete;

	@ManyToOne
	@JoinColumn(name = "cozinha_id")
	private Cozinha cozinha;

	@JsonIgnore
	@ManyToMany
	@ToString.Exclude
	@JoinTable(name = "restaurante_forma_pagamento",
				joinColumns = @JoinColumn(name = "restaurante_id"),
				inverseJoinColumns = @JoinColumn(name = "forma_pagamento_id"))
	private List<FormaPagamento> formasPagamento = new ArrayList<>();

	@Embedded
	@JsonIgnore
	private Endereco endereco;

	@CreationTimestamp
	@JsonIgnore
	@Column(nullable = false, columnDefinition = "datetime")
	private LocalDateTime dataCadastro;

	@UpdateTimestamp
	@JsonIgnore
	@Column(nullable = false, columnDefinition = "datetime")
	private LocalDateTime dataAtualizacao;

	@Override
	public boolean equals (Object o) {
		if(this == o)
			return true;
		if(o == null || Hibernate.getClass(this) != Hibernate.getClass(o))
			return false;
		Restaurante that = (Restaurante) o;
		return Objects.equals(id, that.id);
	}

	@Override
	public int hashCode () {
		return 0;
	}

}
