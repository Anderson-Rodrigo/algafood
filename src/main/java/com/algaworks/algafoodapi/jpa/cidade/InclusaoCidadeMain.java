package com.algaworks.algafoodapi.jpa.cidade;

import com.algaworks.algafoodapi.AlgafoodApiApplication;
import com.algaworks.algafoodapi.domain.model.Cidade;
import com.algaworks.algafoodapi.domain.model.Estado;
import com.algaworks.algafoodapi.domain.repository.CidadeRepository;
import com.algaworks.algafoodapi.domain.repository.EstadoRepository;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ApplicationContext;

import java.util.Optional;

public class InclusaoCidadeMain {

	public static void main(String[] args){
		ApplicationContext applicationContext =  new SpringApplicationBuilder(AlgafoodApiApplication.class)
				.web(WebApplicationType.NONE)
				.run(args);

		CidadeRepository cidadeRepository = applicationContext.getBean(CidadeRepository.class);
		EstadoRepository estadoRepository = applicationContext.getBean(EstadoRepository.class);

		Estado estado = new Estado();
		estado.setId(1L);
		estado.setNome("Santa Catarina");

		estadoRepository.save(estado);
		Optional<Estado> estado1 = estadoRepository.findById(estado.getId());

		Cidade cidade = new Cidade();
		cidade.setId(1L);
		cidade.setNome("Maravilha");
		cidade.setEstado(estado1.get());

		cidadeRepository.save(cidade);

	}
}
