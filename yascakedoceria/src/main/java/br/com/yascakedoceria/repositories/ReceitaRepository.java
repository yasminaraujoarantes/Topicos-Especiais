package br.com.yascakedoceria.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import br.com.yascakedoceria.model.Receita;

@Repository
public interface ReceitaRepository extends JpaRepository<Receita, Long> {

	@Query("Select r from Receita r left join fetch r.receitaProduto where r.id=?1")
	Receita buscarReceitaPorId(Long idReceita);

}
