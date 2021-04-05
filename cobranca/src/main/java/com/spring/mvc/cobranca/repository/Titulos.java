/**
 * 
 */
package com.spring.mvc.cobranca.repository;

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

}
