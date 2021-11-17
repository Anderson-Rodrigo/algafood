package com.algaworks.algafoodapi.jpa.restaurante;

import com.algaworks.algafoodapi.AlgafoodApiApplication;
import com.algaworks.algafoodapi.domain.model.Restaurante;
import com.algaworks.algafoodapi.domain.repository.RestauranteRepository;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ApplicationContext;

import java.util.Optional;

public class BuscaRestauranteMain {

	public static void main(String[] args){
		ApplicationContext applicationContext = new SpringApplicationBuilder(AlgafoodApiApplication.class)
				.web(WebApplicationType.NONE)
				.run(args);

		RestauranteRepository restauranteRepository = applicationContext.getBean(RestauranteRepository.class);
		Optional<Restaurante> restaurante = restauranteRepository.findById(1L);

		System.out.println(restaurante.get().getNome() + "Com taxa de frete de " + restaurante.get().getTaxaFrete());
	}
}
