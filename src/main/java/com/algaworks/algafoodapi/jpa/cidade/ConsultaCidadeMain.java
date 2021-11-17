package com.algaworks.algafoodapi.jpa.cidade;

import com.algaworks.algafoodapi.AlgafoodApiApplication;
import com.algaworks.algafoodapi.domain.model.Cidade;
import com.algaworks.algafoodapi.domain.repository.CidadeRepository;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ApplicationContext;

import java.util.Optional;

public class ConsultaCidadeMain {

	public static void main(String[] args){
		ApplicationContext applicationContext = new SpringApplicationBuilder(AlgafoodApiApplication.class)
				.web(WebApplicationType.NONE)
				.run(args);

		CidadeRepository cidadeRepository = applicationContext.getBean(CidadeRepository.class);

		Optional<Cidade> cidade = cidadeRepository.findById(1L);
		cidade.ifPresent(value -> System.out.println(value.getNome()));


	}
}
