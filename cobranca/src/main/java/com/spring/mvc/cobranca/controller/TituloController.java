/**
 * 
 */
package com.spring.mvc.cobranca.controller;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

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

	private static final String CADASTRO_VIEW = "CadastroTitulo";

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
	public ModelAndView novo() {
		/*
		 * Adiciona no objeto ModelAndView a pagina que sera retornada e os atributos
		 * adicionados
		 */
		ModelAndView mv = new ModelAndView(CADASTRO_VIEW);
		mv.addObject(new Titulo());
		return mv;
	}

	/**
	 * Persiste objeto <b>Titulo</b> no banco de dados. O <b>Spring</b> faz a
	 * conversao automatica dos campos da <i>view</i> para o objeto <b>Titulo</b>
	 * atraves do atributo "name" do HTML.
	 * 
	 * <p>
	 * 		<code>@Validated</code> => O Spring fica encarregado de validar os 
	 * 		campos da entidade baseado nas validacoes do <b>Bean Validation</b>.
	 * </p>
	 * 
	 * <p>
	 * 		<code>Errors</code> => O Spring retorna os erros do formulario.
	 * </p>
	 * 
	 * <p>
	 * 		<code>RedirectAttributes</code> => Redireciona atributos apos o 
	 * 		redirecionamento para uma url da pagina definida no <i>Controller</i>.
	 * </p>
	 * 
	 * @param titulo Objeto retornado da <i>view</i>
	 * @return para a pagina CadastroTitulo
	 */
	@RequestMapping(method = RequestMethod.POST)
	public String salvar(@Validated Titulo titulo, Errors errors, RedirectAttributes attributes) {		
		/*
		 * Valida se existe algum erro no formulario. Caso ocorra, segue com a validacao.
		 */
		if (errors.hasErrors()) {
			return CADASTRO_VIEW; // Retorna para a pagina de Cadastro de Titulos
		}
		
		titulos.save(titulo);
		
		// Adiciona atributos apos o redirecionamento da pagina
		attributes.addFlashAttribute("mensagem", "Título salvo com sucesso!");
		return "redirect:/titulos/novo"; // Novo redirecionamento para a url /titulos/novo
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
	 * O parametro <b>{codigo}</b> indica que o valor esta vindo da URL.
	 * O Spring acessa este parametro pelo @PathVariable e monta a URL de
	 * acordo com o objeto acessado.
	 * 
	 * <p>
	 * 		<code>@PathVariable</code> => O Spring fica encarregado de receber o parametro
	 *		vindo da URL e converter o codigo recebido em uma entidade titulo, como se ja estivesse
	 *		feito a consulta do registro no banco. Essa conversao funciona apenas com o JpaRepository.
	 * </p>
	 * 
	 * @param titulo Objeto retornado da <i>view</i> com o valor do codigo retornado
	 * @return para a pagina CadastroTitulo
	 */
	@RequestMapping("{codigo}")
	public ModelAndView edicao(@PathVariable("codigo") Titulo titulo) {
		ModelAndView mv = new ModelAndView(CADASTRO_VIEW); 
		mv.addObject(titulo);
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
