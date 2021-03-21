package br.com.yascakedoceria.model;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.fasterxml.jackson.annotation.JsonView;

import br.com.yascakedoceria.controllers.View;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data @AllArgsConstructor @NoArgsConstructor
@Entity
@Table(name="receita")
public class Receita implements Serializable{

	private static final long serialVersionUID = 2385147330156175292L;

	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name="id")
	@JsonView(View.ReceitaCompleta.class)
	private Long id;
	
	@Column(name="nome")
	@JsonView({View.ReceitaCompleta.class, View.Receita.class})
	private String nome;
	
	@Column(name="descricao")
	@JsonView({View.ReceitaCompleta.class, View.Receita.class})
	private String descricao;
	
	@Column(name="porcentagem_ganho")
	@JsonView({View.ReceitaCompleta.class, View.Receita.class})
	private double porcentagemDeGanho;
	
	@OneToMany(mappedBy="receita", cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true)
	@JsonView({View.ReceitaCompleta.class, View.Receita.class})
	private List<ReceitaProduto> receitaProduto;
	
	@Transient
	@JsonView(View.ReceitaCompleta.class)
	private double valorIngredientes;
	
	@Transient
	@JsonView(View.ReceitaCompleta.class)
	private double valorDeVenda;
	
	public void setReceitaProduto(List<ReceitaProduto> receitaProduto) {
		if (this.receitaProduto != null) {
			this.receitaProduto.clear();
			this.receitaProduto.addAll(receitaProduto);
		} else {
			this.receitaProduto = receitaProduto;
		}
	}
}
