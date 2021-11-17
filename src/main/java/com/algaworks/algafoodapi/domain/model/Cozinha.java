package com.algaworks.algafoodapi.domain.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;
import org.hibernate.Hibernate;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "tab_cozinha")
@Getter
@Setter
@ToString
@RequiredArgsConstructor
public class Cozinha {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	// para nomear a coluna @JsonProperty
	// para ignorar a coluna @JsonIgnore
	@Column(name = "nom_cozinha")
	private String nome;

	@Override
	public boolean equals (Object o) {
		if(this == o)
			return true;
		if(o == null || Hibernate.getClass(this) != Hibernate.getClass(o))
			return false;
		Cozinha cozinha = (Cozinha) o;
		return Objects.equals(id, cozinha.id);
	}

	@Override
	public int hashCode () {
		return 0;
	}
}
