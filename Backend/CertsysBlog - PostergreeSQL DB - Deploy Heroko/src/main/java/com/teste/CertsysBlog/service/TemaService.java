package com.teste.CertsysBlog.service;

import java.util.Optional;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.teste.CertsysBlog.model.Tema;
import com.teste.CertsysBlog.repository.TemaRepository;

@Service
public class TemaService {
	
	@Autowired
	private  TemaRepository repositorioT;
	
	public Optional<Tema> alterarTema(Tema temaParaAlterar) {
		return repositorioT.findById(temaParaAlterar.getId()).map(temaExistente -> {
			temaExistente.setDescricao(temaParaAlterar.getDescricao());
			return Optional.ofNullable(repositorioT.save(temaExistente));
		}).orElseGet(() -> {
			return Optional.empty();
		});
	}

}
