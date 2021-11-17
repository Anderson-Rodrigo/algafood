package com.algaworks.algafoodapi.domain.service;

import com.algaworks.algafoodapi.domain.exception.EntidadeNaoEncontradaException;
import com.algaworks.algafoodapi.domain.infrastructure.RestauranteRepositoryImpl;
import com.algaworks.algafoodapi.domain.model.Cozinha;
import com.algaworks.algafoodapi.domain.model.Restaurante;
import com.algaworks.algafoodapi.domain.repository.RestauranteRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ReflectionUtils;

import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class RestauranteService {

	@Autowired
	private RestauranteRepository restauranteRepository;

	@Autowired
	private CozinhaService cozinhaService;

	private final RestauranteRepositoryImpl restauranteRepositoryImpl;

	public RestauranteService(RestauranteRepositoryImpl restauranteRepositoryImpl) {
		this.restauranteRepositoryImpl = restauranteRepositoryImpl;
	}

	public Restaurante salvar(Restaurante restaurante){
		Long idCozinha = restaurante.getCozinha().getId();
		Optional<Cozinha> cozinha = cozinhaService.buscar(idCozinha);

		if (cozinha.isEmpty()){
			throw new EntidadeNaoEncontradaException(String.format("Não foi encontrada nenhuma cozinha com o id %d", idCozinha));
		}
		restaurante.setCozinha(cozinha.get());

		return restauranteRepository.save(restaurante);
	}

	public Optional<Restaurante> buscar(Long id){
		return restauranteRepository.findById(id);
	}

	public List<Restaurante> lista(){
		return restauranteRepository.findAll();
	}

	public void remover(Long id){
		Optional<Restaurante> restaurante = restauranteRepository.findById(id);
		if(restaurante.isEmpty()){
			throw new EntidadeNaoEncontradaException(String.format("Não foi encontrado nenhum restaurante com id %d", id));
		}
		restauranteRepository.deleteById(id);
	}

	public Restaurante atualizar(Long id, Restaurante restaurante){
		Optional<Restaurante> buscouRestaurante = restauranteRepository.findById(id);

		if(buscouRestaurante != null) {
			BeanUtils.copyProperties(restaurante, buscouRestaurante.get(), "id");
		}

		return restauranteRepository.save(buscouRestaurante.get());
	}

	public Restaurante atualizarAlgumasInfo(Long id, Map<String, Object> campos){
		Optional<Restaurante> buscouRestaurante = restauranteRepository.findById(id);

		if(buscouRestaurante.isEmpty() ){
			throw new EntidadeNaoEncontradaException(String.format("Restaurante com id %d nao foi encontrado", id));
		}else{
			merge(campos, buscouRestaurante.get());
			return restauranteRepository.save(buscouRestaurante.get());
		}
	}

	private void merge(Map<String, Object> dadosOrigem, Restaurante restauranteDestino){
		ObjectMapper objectMapper = new ObjectMapper();
		Restaurante restauranteOrigem = objectMapper.convertValue(dadosOrigem, Restaurante.class);

		dadosOrigem.forEach((nomePropriedade, valorPropriedade) -> {
			Field field = ReflectionUtils.findField(Restaurante.class, nomePropriedade);
			field.setAccessible(true);

			Object object = ReflectionUtils.getField(field, restauranteOrigem);

			ReflectionUtils.setField(field, restauranteDestino, object);
		});
	}

	public List<Restaurante>  buscarPorNome(String nome, BigDecimal taxaFreteInicial, BigDecimal taxaFreteFinaL){
		return restauranteRepositoryImpl.listar(nome, taxaFreteInicial, taxaFreteFinaL);

	}

}
