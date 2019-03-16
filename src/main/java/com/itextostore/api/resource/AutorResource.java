package com.itextostore.api.resource;

import java.net.URI;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
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

import com.itextostore.api.model.Autor;
import com.itextostore.api.repository.AutorRepository;
import com.itextostore.api.services.AutorService;

@RestController
@RequestMapping("/autores")
public class AutorResource {
	
	@Autowired
	private AutorRepository autorRepository;
	
	@Autowired
	private AutorService autorService;
	
	@GetMapping
	public List<Autor> listar() {
		return autorRepository.findAll();
	}
	
	@PostMapping
	public ResponseEntity<Autor> criar(@Valid @RequestBody Autor autor) {
		Autor autorSalvo = autorRepository.save(autor);
		
		URI uri = ServletUriComponentsBuilder.fromCurrentRequestUri().path("/{id}")
					.buildAndExpand(autorSalvo.getId()).toUri();

		return ResponseEntity.created(uri).body(autorSalvo);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Autor> buscarPeloId(@PathVariable Long id) {
		Autor autor = autorRepository.findOne(id);
		return autor != null? ResponseEntity.ok(autor) : ResponseEntity.notFound().build();
	}
	
	@DeleteMapping("/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void remover(@PathVariable Long id) {
		autorRepository.delete(id);
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<Autor> atualizar(@PathVariable Long id, @Valid @RequestBody Autor autor) {
		Autor autorSalvo = autorService.atualizar(id, autor);
		return ResponseEntity.ok(autorSalvo);
	}

}
