package com.algaworks.algafoodapi.domain.model;

import com.algaworks.algafoodapi.Groups;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.Hibernate;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "tab_cozinha")
@Getter
@Setter
@ToString
@RequiredArgsConstructor
public class Cozinha {
	@NotNull(groups = Groups.CadastroRestaurante.class)
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	// para nomear a coluna @JsonProperty
	// para ignorar a coluna @JsonIgnore
	@NotBlank
	@Column(name = "nom_cozinha")
	private String nome;

	@JsonIgnore
	@OneToMany(mappedBy = "cozinha")
	@ToString.Exclude
	private List<Restaurante> restaurantes = new ArrayList<>();

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
