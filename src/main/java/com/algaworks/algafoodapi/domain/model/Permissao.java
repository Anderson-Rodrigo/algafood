package com.algaworks.algafoodapi.domain.model;

import lombok.*;
import org.hibernate.Hibernate;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "tab_permissao")
@Getter
@Setter
@ToString
@RequiredArgsConstructor
public class Permissao {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "per_nome")
	private String nome;

	@Column(name = "per_descricao")
	private String descricao;

	@Override
	public boolean equals (Object o) {
		if(this == o)
			return true;
		if(o == null || Hibernate.getClass(this) != Hibernate.getClass(o))
			return false;
		Permissao permissao = (Permissao) o;
		return Objects.equals(id, permissao.id);
	}

	@Override
	public int hashCode () {
		return 0;
	}
}
