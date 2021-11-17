package com.algaworks.algafoodapi.domain.model;

import lombok.*;
import org.hibernate.Hibernate;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "tab_forma_pagamento")
@Getter
@Setter
@ToString
@RequiredArgsConstructor
public class FormaPagamento {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long Id;

	@Column(name = "pag_descricao")
	private String descricao;

	@Override
	public boolean equals (Object o) {
		if(this == o)
			return true;
		if(o == null || Hibernate.getClass(this) != Hibernate.getClass(o))
			return false;
		FormaPagamento that = (FormaPagamento) o;
		return Objects.equals(Id, that.Id);
	}

	@Override
	public int hashCode () {
		return 0;
	}
}
