/**
 * 
 */
package com.spring.mvc.cobranca.model;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.DecimalMax;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.NumberFormat;

import lombok.Getter;
import lombok.Setter;


/**
 * @author dev
 *
 * Entidade que representa o objeto titulo para persistencia dos dados.
 * Para consultas em banco de memoria
 */
@Entity
public class Titulo {

	@Id // Define o atributo como chave primaria
	@Getter // Cria o Get do atributo
	@Setter // Cria o Setter do atributo
	@GeneratedValue(strategy = GenerationType.IDENTITY) // Define o atributo como auto-incremento
	private Long codigo;

	@Getter // Cria o Get do atributo
	@Setter // Cria o Setter do atributo
	@NotEmpty(message = "Descrição é obrigatória") // Retorna mensagem de erro para campo vazio
	@Size(max = 60, message = "A descrição não pode conter mais de 60 caracteres") // Retorna mensagem de erro para valor com mais de 60 caracteres
	private String descricao;

	/**
	 * Define um padrao de data para persistencia.
	 */
	@Getter // Cria o Get do atributo
	@Setter // Cria o Setter do atributo
	@NotNull(message = "Date de vencimento é obrigatória") // Retorna mensagem de erro para valor nulo
	@DateTimeFormat(pattern = "dd/MM/yyyy") // Formatacao da Data
	@Temporal(TemporalType.DATE) // Define o tipo de campo que sera salvo no banco de dados
	private Date dataVencimento;

	/**
	 * Define um formato para os valores.
	 */
	@Getter // Cria o Get do atributo
	@Setter // Cria o Setter do atributo
	@NotNull(message = "Valor é obrigatório") // Retorna mensagem de erro para valor nulo
	@DecimalMin(value = "0.01", message = "Valor não pode ser menor que 0,01") // Retorna mensagem de erro para valor menor que 0.01
	@DecimalMax(value = "9999999.99", message = "Valor não pode ser maior que 9.999.999,99") // Retorna mensagem de erro para valor maior que 9.999.999,99
	@NumberFormat(pattern = "#,##0.00") // Formatacao do valor em numero decimal
	private BigDecimal valor;

	@Getter // Cria o Get do atributo
	@Setter // Cria o Setter do atributo
	@Enumerated(EnumType.STRING) // Converte o valor do ENUM em String ao persistir
	private StatusTitulo status;
	
	public Long getCodigo() {
		return codigo;
	}

	public void setCodigo(Long codigo) {
		this.codigo = codigo;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public Date getDataVencimento() {
		return dataVencimento;
	}

	public void setDataVencimento(Date dataVencimento) {
		this.dataVencimento = dataVencimento;
	}

	public BigDecimal getValor() {
		return valor;
	}

	public void setValor(BigDecimal valor) {
		this.valor = valor;
	}

	public StatusTitulo getStatus() {
		return status;
	}

	public void setStatus(StatusTitulo status) {
		this.status = status;
	}

	public boolean isPendente() {
		return StatusTitulo.PENDENTE.equals(this.status);
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((codigo == null) ? 0 : codigo.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Titulo other = (Titulo) obj;
		if (codigo == null) {
			if (other.codigo != null)
				return false;
		} else if (!codigo.equals(other.codigo))
			return false;
		return true;
	}

}
