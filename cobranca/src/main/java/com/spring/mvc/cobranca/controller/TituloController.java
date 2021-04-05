/**
 * 
 */
package com.spring.mvc.cobranca.controller;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.spring.mvc.cobranca.model.StatusTitulo;
import com.spring.mvc.cobranca.model.Titulo;
import com.spring.mvc.cobranca.repository.Titulos;

/**
 * @author dev
 *
 *         <p>
 *         Mapeamento para a pagina de titulos.
 *         </p>
 */
@Controller
@RequestMapping("/titulos")
public class TituloController {

	/**
	 * <b>Autowired</b> faz a inicializacao do objeto.
	 */
	@Autowired
	private Titulos titulos;

	/**
	 * Para que o endpoint identifique a pagina <b>HTML</b>, o metodo precisa
	 * retornar uma <i>String</i>, sempre com o nome exato da pagina HTML
	 * desenvolvida para o <b>Controller</b>.
	 * 
	 * @return para pagina CadastroTitulo
	 */
	@RequestMapping("/novo")
	public String novo() {
		return "CadastroTitulo";
	}

	/**
	 * Persiste objeto <b>Titulo</b> no banco de dados. O <b>Spring</b> faz a
	 * conversao automatica dos campos da <i>view</i> para o objeto <b>Titulo</b>
	 * atraves do atributo "name" do HTML.
	 * 
	 * @param titulo Objeto retornado da <i>view</i>
	 * @return para a pagina CadastroTitulo
	 */
	@RequestMapping(method = RequestMethod.POST)
	public ModelAndView salvar(Titulo titulo) {
		titulos.save(titulo);

		/*
		 * Adiciona no objeto ModelAndView a pagina que sera retornada e os atributos
		 * adicionados
		 */
		ModelAndView mv = new ModelAndView("CadastroTitulo");
		mv.addObject("mensagem", "Título salvo com sucesso!");
		return mv;
	}
	
	/**
	 * @return todos os titulos adicionados
	 */
	@RequestMapping
	public ModelAndView pesquisar() {
		List<Titulo> todosTitulos = titulos.findAll();
		ModelAndView mv = new ModelAndView("PesquisaTitulos");
		mv.addObject("titulos", todosTitulos);
		return mv;
	}

	/**
	 * Deixa disponivel um atributo em todas as paginas do <b>Controller</b>.
	 * 
	 * <p>
	 * Caso nao seja adicionado um nome para o atributo, por padrao o
	 * <b>Thymeleaf</b> entende:
	 * 
	 * <pre>
	 *	objeto + tipo de objeto
	 *	<code><b>statusTituloList</b></code>
	 * </pre>
	 * </p>
	 * 
	 * @return todos os status do titulo na view
	 */
	@ModelAttribute("todosStatusTitulo")
	public List<StatusTitulo> todosStatusTitulo() {
		return Arrays.asList(StatusTitulo.values());
	}
}
