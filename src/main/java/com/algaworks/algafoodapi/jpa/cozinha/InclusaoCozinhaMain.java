package com.algaworks.algafoodapi.jpa.cozinha;

import com.algaworks.algafoodapi.AlgafoodApiApplication;
import com.algaworks.algafoodapi.domain.model.Cozinha;
import com.algaworks.algafoodapi.domain.repository.CozinhaRepository;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ApplicationContext;

import java.util.List;

public class InclusaoCozinhaMain {

	public static void main(String[] args) {
		ApplicationContext applicationContext = new SpringApplicationBuilder(AlgafoodApiApplication.class)
				.web(WebApplicationType.NONE)
				.run(args);

		CozinhaRepository cadastroCozinha = applicationContext.getBean(CozinhaRepository.class);

		Cozinha cozinha1 = new Cozinha();
		cozinha1.setNome("Jamponesa");

		Cozinha cozinha2 = new Cozinha();
		cozinha1.setNome("Brasileira");

		cadastroCozinha.save(cozinha1);
		cadastroCozinha.save(cozinha2);
	}
}
