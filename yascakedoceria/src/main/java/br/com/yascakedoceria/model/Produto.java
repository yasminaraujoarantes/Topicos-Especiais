package br.com.yascakedoceria.model;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonView;

import br.com.yascakedoceria.controllers.View;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data @AllArgsConstructor @NoArgsConstructor
@Entity
@Table(name="produto")
public class Produto implements Serializable{

	private static final long serialVersionUID = 9121630298982380035L;

	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name="id")
	@JsonView({View.ProdutoCompleto.class, View.Receita.class})
	private Long id;
	
	@Column(name="nome")
	@JsonView({View.Produto.class, View.ReceitaCompleta.class})
	private String nome;
	
	@Column(name="unidade_medida")
	@JsonView(View.Produto.class)
	private String unidadeMedida;
	
	@Column(name="quantidade")
	@JsonView(View.Produto.class)
	private double quantidade;
	
	@Column(name="valor")
	@JsonView(View.Produto.class)
	private double valor;
	
	@Column(name="local")
	@JsonView(View.Produto.class)
	private String local;
	
	@Column(name="data_valor")
	@JsonView(View.ProdutoCompleto.class)
	private Date dataValor;

	@OneToMany(mappedBy="produto")
	private List<ReceitaProduto> receitaProduto;
}
