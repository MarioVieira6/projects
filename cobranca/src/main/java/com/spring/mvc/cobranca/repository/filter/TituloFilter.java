/**
 * 
 */
package com.spring.mvc.cobranca.repository.filter;

import lombok.Getter;
import lombok.Setter;

/**
 * Objeto que recebe o filtro da pesquisa de titulo.
 */
public class TituloFilter {
	
	@Getter
	@Setter
	private String descricao;

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
}
