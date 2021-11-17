package com.algaworks.algafoodapi.domain.model;

import com.sun.istack.NotNull;
import lombok.*;
import org.hibernate.Hibernate;
import org.springframework.data.annotation.Reference;

import javax.persistence.*;
import java.math.BigDecimal;
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
