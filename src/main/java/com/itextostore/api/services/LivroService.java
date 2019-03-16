package com.itextostore.api.services;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.itextostore.api.model.Livro;
import com.itextostore.api.repository.LivroRepository;

@Service
public class LivroService {
	
	@Autowired
	private LivroRepository livroRepository;
	
	
	public Livro atualizar(Long id, Livro livro) {
		Livro livroSalvo = buscarLivroExistente(id);

		BeanUtils.copyProperties(livro, livroSalvo, "id");

		return livroRepository.save(livroSalvo);
	}
	
	private Livro buscarLivroExistente(Long id) {
		Livro livroSalvo = livroRepository.findOne(id);
		if (livroSalvo == null) {
			throw new IllegalArgumentException();
		}
		return livroSalvo;
	}

}
