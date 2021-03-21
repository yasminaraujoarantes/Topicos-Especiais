package br.com.yascakedoceria.services;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.yascakedoceria.model.Produto;
import br.com.yascakedoceria.repositories.ProdutoRepository;

@Service
public class ProdutoService {
	
	@Autowired
	private ProdutoRepository repository;
	
	public Produto findById(Long idProduto) throws Exception {
		Optional<Produto> obj = repository.findById(idProduto);
		return obj.orElseThrow(() -> new Exception("Não encontrado!"));
	}
	
	public Produto inserirProduto(Produto produtoView) {
		produtoView.setDataValor(new Date());
		return repository.save(produtoView);
	}
	
	public Produto editarProduto(Produto produtoView, Long idProduto) throws Exception {
		Produto produto = findById(idProduto);
		
		produto.setNome(produtoView.getNome());
		produto.setUnidadeMedida(produtoView.getUnidadeMedida());
		produto.setLocal(produtoView.getLocal());
		
		if (produtoView.getValor() != produto.getValor() || produtoView.getQuantidade() != produto.getQuantidade()) {
			produto.setQuantidade(produtoView.getQuantidade());
			produto.setValor(produtoView.getValor());
			produto.setDataValor(new Date());
		}
		
		return repository.save(produto);
	}
	
	public void deletarProduto(Long idProduto) throws Exception {
		findById(idProduto);
		repository.deleteById(idProduto);
	}

	public Produto buscarProduto(Long idProduto) {
		return repository.buscarProduto(idProduto);
	}

	public List<Produto> buscarProdutoPorNomeQtd(String nomeProduto, double quantidade) {
		return repository.buscarProdutoPorNomeQtd(nomeProduto, quantidade);
	}

}
