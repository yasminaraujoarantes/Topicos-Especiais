package br.com.yascakedoceria.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import br.com.yascakedoceria.model.Produto;

@Repository
public interface ProdutoRepository extends JpaRepository<Produto, Long> {
	
	@Query("select p from Produto p where p.id = ?1")
	Produto buscarProduto(Long idProduto);

	@Query("select p from Produto p where p.nome like %?1% and p.quantidade = ?2")
	List<Produto> buscarProdutoPorNomeQtd(String nomeProduto, double quantidade);

}
