package com.algaworks.algafoodapi.domain.model;

import lombok.*;
import org.hibernate.Hibernate;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "tab_estado")
@Getter
@Setter
@ToString
@RequiredArgsConstructor
public class Estado {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "est_nome")
	private String nome;

	@Override
	public boolean equals (Object o) {
		if(this == o)
			return true;
		if(o == null || Hibernate.getClass(this) != Hibernate.getClass(o))
			return false;
		Estado estado = (Estado) o;
		return Objects.equals(id, estado.id);
	}

	@Override
	public int hashCode () {
		return 0;
	}
}
