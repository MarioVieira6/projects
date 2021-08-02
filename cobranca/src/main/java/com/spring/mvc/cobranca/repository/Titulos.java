/**
 * 
 */
package com.spring.mvc.cobranca.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.spring.mvc.cobranca.model.Titulo;

/**
 * @author dev
 *
 * Todo repositorio devera ser representado na forma plural da entidade
 * relacionada. O <b>Spring Data</b> ja disponibiliza uma implementacao
 * generica dos principais metodos de persistencia.
 * 
 */
public interface Titulos extends JpaRepository<Titulo, Long> {

	/**
	 * Consulta montada de acordo com o <b>Spring Data</b>.
	 * 
	 * <p>
	 * Documento de Referencia: 
	 * <a href="https://docs.spring.io/spring-data/jpa/docs/2.5.3/reference/html/#jpa.query-methods.query-creation">
	 * JPA Query Methods
	 * </a>
	 * </p>
	 * 
	 * @param descricao Filtro da consulta
	 * @return titulos de acordo com o filtro
	 */
	public List<Titulo> findByDescricaoContaining(String descricao);
}
