package com.itextostore.api.services;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.itextostore.api.model.Autor;
import com.itextostore.api.repository.AutorRepository;

@Service
public class AutorService {
	
	@Autowired
	private AutorRepository autorRepository;
	
	public Autor atualizar(Long id, Autor autor) {
		Autor autorSalvo = autorRepository.findOne(id);
		if (autorSalvo == null ) {
			throw new EmptyResultDataAccessException(1);
		}
		BeanUtils.copyProperties(autor, autorSalvo, "id");
		return autorRepository.save(autorSalvo);
	}

}
