package com.itextostore.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.itextostore.api.model.Livro;
import com.itextostore.api.repository.livro.LivroRepositoryQuery;

public interface LivroRepository extends JpaRepository<Livro, Long>, LivroRepositoryQuery {

}
