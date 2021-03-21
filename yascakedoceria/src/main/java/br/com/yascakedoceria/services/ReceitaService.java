package br.com.yascakedoceria.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.yascakedoceria.model.Receita;
import br.com.yascakedoceria.model.ReceitaProduto;
import br.com.yascakedoceria.repositories.ReceitaRepository;

@Service
public class ReceitaService {
	
	@Autowired
	private ReceitaRepository repository;
	
	@Autowired
	private ProdutoService produtoService;
	
	private Receita buscarPorId(Long idReceita) throws Exception {
		Optional<Receita> obj = repository.findById(idReceita);
		return obj.orElseThrow(() -> new Exception("Não encontrado!"));
	}
	
	public Receita inserirReceita(Receita receitaView) throws Exception {

		for (ReceitaProduto element : receitaView.getReceitaProduto()) {
			element.setProduto(produtoService.findById(element.getProduto().getId()));
			element.setReceita(receitaView);
		}		
		return repository.save(receitaView);
	}
	
	public void deletarReceita(Long idReceita) throws Exception {
		buscarPorId(idReceita);
		repository.deleteById(idReceita);
	}

	public Receita buscarReceita(Long idReceita) {
		Receita receita = repository.buscarReceitaPorId(idReceita);
		
		double valorProdutos = 0;
		for (ReceitaProduto item : receita.getReceitaProduto()) {
			double valor = (item.getQuantidadeUtilizada() * item.getProduto().getValor())/item.getProduto().getQuantidade();
			valorProdutos += valor;
		}
		
		receita.setValorIngredientes(valorProdutos);
		receita.setValorDeVenda(receita.getValorIngredientes()*receita.getPorcentagemDeGanho()/100+receita.getValorIngredientes());
		
		return receita;
	}

}
