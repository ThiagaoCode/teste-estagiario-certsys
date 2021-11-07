package com.teste.CertsysBlog.controller;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.teste.CertsysBlog.model.Postagem;
import com.teste.CertsysBlog.repository.PostagemRepository;
import com.teste.CertsysBlog.service.PostagemService;

@RestController
@RequestMapping("/postagens")
@CrossOrigin("*")
public class PostagemController {
	
	@Autowired
	private PostagemRepository repository;

	@Autowired
	private PostagemService servicos;

	@GetMapping
	public ResponseEntity<List<Postagem>> GetAll() {
		return ResponseEntity.ok(repository.findAll());
	}

	@GetMapping("/{id}")
	public ResponseEntity<Postagem> GetById(@PathVariable Long id) {
		return repository.findById(id).map(resp -> ResponseEntity.ok(resp)).orElse(ResponseEntity.notFound().build());
	}

	@GetMapping("/titulo/{titulo}")
	public ResponseEntity<List<Postagem>> GetByTitulo(@PathVariable String titulo) {
		return ResponseEntity.ok(repository.findAllByTituloContainingIgnoreCase(titulo));

	}
	
	@PostMapping("/salvar")
	public ResponseEntity<Object> cadastrarPostagem(@Valid @RequestBody Postagem novaPostagem) {
		Optional<?> objetoCadastrado = servicos.cadastrarPostagem(novaPostagem);

		if (objetoCadastrado.isPresent()) {
			return ResponseEntity.status(201).body(objetoCadastrado.get());
		} else {
			return ResponseEntity.status(400).build();
		}

	}
	
	
	@PutMapping("/alterar")
	public ResponseEntity<Object> alterar(@Valid @RequestBody Postagem postagemParaAlterar) {
		Optional<Postagem> objetoAlterado = servicos.alterarPostagem(postagemParaAlterar);

		if (objetoAlterado.isPresent()) {
			return ResponseEntity.status(201).body(objetoAlterado.get());
		} else {
			return ResponseEntity.status(400).build();
		}
	}

	

	@DeleteMapping("/deletar/{id}")
	public ResponseEntity<Object> deletarPorId(@PathVariable(value = "id") Long id) {
		Optional<Postagem> objetoExistente = repository.findById(id);
		if (objetoExistente.isPresent()) {
			repository.deleteById(id);
			return ResponseEntity.status(200).build();
		} else {
			return ResponseEntity.status(400).build();
		}

	}

}
