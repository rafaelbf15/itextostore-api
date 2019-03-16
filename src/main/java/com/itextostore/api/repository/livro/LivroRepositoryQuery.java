package com.itextostore.api.repository.livro;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.itextostore.api.model.Livro;
import com.itextostore.api.repository.filter.LivroFilter;

public interface LivroRepositoryQuery {
	
	public Page<Livro> filtrar(LivroFilter livroFilter, Pageable pageable);

}
