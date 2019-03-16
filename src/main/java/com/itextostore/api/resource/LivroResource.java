package com.itextostore.api.resource;

import java.net.URI;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.itextostore.api.model.Livro;
import com.itextostore.api.repository.LivroRepository;
import com.itextostore.api.repository.filter.LivroFilter;
import com.itextostore.api.services.LivroService;

@RestController
@RequestMapping("/livros")
public class LivroResource {
	
	@Autowired
	private LivroRepository livroRepository;
	
	@Autowired
	private LivroService livroService;
	
	@GetMapping
	public Page<Livro> listar(LivroFilter livroFilter, Pageable pageable) {
		return livroRepository.filtrar(livroFilter, pageable);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Livro> buscarPeloId(@PathVariable Long id) {
		Livro livro = livroRepository.findOne(id);
		return livro != null ? ResponseEntity.ok(livro) : ResponseEntity.notFound().build();
	}
	
	@PostMapping
	public ResponseEntity<Livro> criar(@Valid @RequestBody Livro livro) {
		Livro livroSalvo = livroRepository.save(livro);
		
		URI uri = ServletUriComponentsBuilder.fromCurrentRequestUri().path("/{id}")
				.buildAndExpand(livroSalvo.getId()).toUri();
		
		return ResponseEntity.created(uri).body(livroSalvo);
	}
	
	@DeleteMapping("/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void remover(@PathVariable Long id) {
		livroRepository.delete(id);
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<Livro> atualizar(@PathVariable Long id, @Valid @RequestBody Livro livro) {
		try {
			Livro livroSalvo = livroService.atualizar(id, livro);
			return ResponseEntity.ok(livroSalvo);
		} catch (IllegalArgumentException e) {
			return ResponseEntity.notFound().build();
		}
	}

}
