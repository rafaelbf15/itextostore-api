package com.itextostore.api.repository.livro;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.util.StringUtils;

import com.itextostore.api.model.Autor;
import com.itextostore.api.model.Autor_;
import com.itextostore.api.model.Livro;
import com.itextostore.api.model.Livro_;
import com.itextostore.api.repository.filter.LivroFilter;

public class LivroRepositoryImpl implements LivroRepositoryQuery{
	
	@PersistenceContext
	private EntityManager manager;
	
	@Override
	public Page<Livro> filtrar(LivroFilter livroFilter, Pageable pageable) {
		CriteriaBuilder builder = manager.getCriteriaBuilder();
		CriteriaQuery<Livro> criteria = builder.createQuery(Livro.class);
		
		Root<Livro> root = criteria.from(Livro.class);
		Join<Livro, Autor> autor = root.join(Livro_.autor);
		
		Predicate[] predicates = criarRestricoes(livroFilter, builder, root, autor);
		criteria.where(predicates);
		criteria.orderBy(builder.desc(root.get(Livro_.preco)));
		
		TypedQuery<Livro> query = manager.createQuery(criteria);
		adicionarRestricoesDePaginacao(query, pageable);
		
		return new PageImpl<>(query.getResultList(), pageable, total(livroFilter));
	}

	private Predicate[] criarRestricoes(LivroFilter livroFilter, CriteriaBuilder builder, 
			Root<Livro> root, Join<Livro, Autor> autor) {
		
		List<Predicate> predicates = new ArrayList<>();
		
		if (!StringUtils.isEmpty(livroFilter.getPesquisa())) {
			Predicate predicateTitulo = builder.like(builder.lower(root.get(Livro_.titulo)),"%" + livroFilter.getPesquisa().toLowerCase() + "%");
			Predicate predicateAutor = builder.like(builder.lower(autor.get(Autor_.nome)),"%" + livroFilter.getPesquisa().toLowerCase() + "%");
			predicates.add(builder.or(predicateTitulo, predicateAutor));
		}
		
		return predicates.toArray(new Predicate[predicates.size()]);
	}

	private void adicionarRestricoesDePaginacao(TypedQuery<Livro> query, Pageable pageable) {
		int paginaAtual = pageable.getPageNumber();
		int totalRegistroPorPagina = pageable.getPageSize();
		int primeiroRegistroPagina = paginaAtual * totalRegistroPorPagina;
		
		query.setFirstResult(primeiroRegistroPagina);
		query.setMaxResults(totalRegistroPorPagina);
	}
	
	private Long total(LivroFilter livroFilter) {
		CriteriaBuilder builder = manager.getCriteriaBuilder();
		CriteriaQuery<Long> criteria = builder.createQuery(Long.class);
		Root<Livro> root = criteria.from(Livro.class);
		Join<Livro, Autor> autor = root.join(Livro_.autor);
		
		
		Predicate[] predicates = criarRestricoes(livroFilter, builder, root, autor);
		criteria.where(predicates);
		
		criteria.select(builder.count(root));
		
		return manager.createQuery(criteria).getSingleResult();
	}
	
}
