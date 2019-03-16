package com.itextostore.api.model;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Livro.class)
public abstract class Livro_ {

	public static volatile SingularAttribute<Livro, Double> preco;
	public static volatile SingularAttribute<Livro, Double> peso;
	public static volatile SingularAttribute<Livro, String> titulo;
	public static volatile SingularAttribute<Livro, Long> id;
	public static volatile SingularAttribute<Livro, Autor> autor;

}

