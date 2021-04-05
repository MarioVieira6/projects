/**
 * 
 */
package com.spring.mvc.cobranca.model;

/**
 * @author dev
 * 
 * Status do Titulo.
 */
public enum StatusTitulo {

	PENDENTE("Pendente"),
	RECEBIDO("Recebido");
	
	private String descricao;
	
	StatusTitulo(String descricao) {
		this.descricao = descricao;
	}
	
	public String getDescricao() {
		return descricao;
	}
	
}
