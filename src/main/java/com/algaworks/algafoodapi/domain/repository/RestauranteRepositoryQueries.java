package com.algaworks.algafoodapi.domain.repository;

import com.algaworks.algafoodapi.domain.model.Restaurante;

import java.math.BigDecimal;
import java.util.List;

public interface RestauranteRepositoryQueries {

	List<Restaurante> listar (String nome, BigDecimal taxaFreteIncial, BigDecimal taxaFreteFinal);

}
