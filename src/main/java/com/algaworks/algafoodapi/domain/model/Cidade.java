package com.algaworks.algafoodapi.domain.model;

import lombok.*;
import org.hibernate.Hibernate;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "tab_cidade")
@Getter
@Setter
@ToString
@RequiredArgsConstructor
public class Cidade {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "cid_nome")
	private String nome;

	@ManyToOne
	@JoinColumn(name = "cid_est_id")
	private Estado estado;

	@Override
	public boolean equals (Object o) {
		if(this == o)
			return true;
		if(o == null || Hibernate.getClass(this) != Hibernate.getClass(o))
			return false;
		Cidade cidade = (Cidade) o;
		return Objects.equals(id, cidade.id);
	}

	@Override
	public int hashCode () {
		return 0;
	}

}
