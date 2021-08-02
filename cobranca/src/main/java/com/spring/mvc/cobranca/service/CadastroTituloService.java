/**
 * 
 */
package com.spring.mvc.cobranca.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import com.spring.mvc.cobranca.model.StatusTitulo;
import com.spring.mvc.cobranca.model.Titulo;
import com.spring.mvc.cobranca.repository.Titulos;
import com.spring.mvc.cobranca.repository.filter.TituloFilter;

/**
 * Camada de Servico para tratar das regras de negocio do sistema.
 */
@Service
public class CadastroTituloService {
	
	@Autowired
	private Titulos titulos;

	/**
	 * Cadastro de titulo.
	 * 
	 * @param titulo Titulo que sera persistido
	 */
	public void salvar(Titulo titulo) {
		try {
			titulos.save(titulo);
		} catch (DataIntegrityViolationException e) {
			throw new IllegalArgumentException("Formato de data inválido");
		}
	}

	/**
	 * Exclusao de titulo.
	 * 
	 * @param codigo Codigo do titulo selecionado
	 */
	public void excluir(Long codigo) {
		titulos.deleteById(codigo);
	}
	
	/**
	 * Atualiza status do titulo Pendente para Recebido.
	 * 
	 * @param codigo Codigo do titulo selecionado
	 * @return Resposta da atualizacao
	 */
	public String receber(Long codigo) {
		Titulo titulo = titulos.findById(codigo).get();
		titulo.setStatus(StatusTitulo.RECEBIDO);
		titulos.save(titulo);
		
		return StatusTitulo.RECEBIDO.getDescricao();
	}
	
	/**
	 * Consulta de titulos de acordo com o filtro.
	 * 
	 * @param filtro Filtro da pesquisa
	 * @return resultados da pesquisa
	 */
	public List<Titulo> filtrar(TituloFilter filtro) {
		String descricao = filtro.getDescricao() == null ? "" : filtro.getDescricao();
		return titulos.findByDescricaoContaining(descricao);
	}
}
