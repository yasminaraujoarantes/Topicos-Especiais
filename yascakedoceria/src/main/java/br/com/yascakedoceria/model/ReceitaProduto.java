package br.com.yascakedoceria.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonView;

import br.com.yascakedoceria.controllers.View;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data @AllArgsConstructor @NoArgsConstructor
@Entity
@Table(name="receita_produto")
public class ReceitaProduto implements Serializable{

	private static final long serialVersionUID = 4944518479787306938L;

	@Id
	@ManyToOne
	@JoinColumn(name = "produto_id", referencedColumnName = "id")
	@JsonView({View.ReceitaCompleta.class, View.Receita.class})
	private Produto produto;

	@Id
	@ManyToOne
	@JoinColumn(name = "receita_id", referencedColumnName = "id")
	@JsonView({View.ReceitaCompleta.class, View.Receita.class})
	private Receita receita;
	
	@Column(name="quantidade")
	@JsonView({View.ReceitaCompleta.class, View.Receita.class})
	private double quantidadeUtilizada;
	
	@Column(name="unidade_medida")
	@JsonView({View.ReceitaCompleta.class, View.Receita.class})
	private String unidadeMedida;
	
}
