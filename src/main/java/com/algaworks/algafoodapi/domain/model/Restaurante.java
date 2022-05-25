package com.algaworks.algafoodapi.domain.model;

import com.algaworks.algafoodapi.Groups;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.Hibernate;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.groups.ConvertGroup;
import javax.validation.groups.Default;
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

	@NotBlank
	@Column(name = "nom_restaurante")
	private String nome;

	@Column(name = "taxa_frete", nullable = false)
	private BigDecimal taxaFrete;

	@Valid
	@NotNull
	@ConvertGroup(from = Default.class, to = Groups.CozinhaId.class)
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "cozinha_id", nullable = false)
	//@JsonIgnore
	private Cozinha cozinha;

	@ManyToMany
	//@JsonIgnore
	@JoinTable(name = "restaurante_forma_pagamento",
				joinColumns = @JoinColumn(name = "tab_restaurante_id"),
				inverseJoinColumns = @JoinColumn(name = "tab_forma_pagamento_id"))
	private List<FormaPagamento> formasPagamento = new ArrayList<>();

	@OneToMany(mappedBy = "restaurante")
	@ToString.Exclude
	@JsonIgnore
	private List<Produto> produtos = new ArrayList<>();

	@Embedded
	@JsonIgnore
	private Endereco endereco;

	@CreationTimestamp
	@JsonIgnore
	@Column(name = "data_cadastro", nullable = false, columnDefinition = "datetime")
	private LocalDateTime dataCadastro;

	@UpdateTimestamp
	@JsonIgnore
	@Column(name = "data_atualizacao", nullable = false, columnDefinition = "datetime")
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
