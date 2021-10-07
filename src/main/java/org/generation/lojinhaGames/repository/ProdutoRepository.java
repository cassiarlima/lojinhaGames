package org.generation.lojinhaGames.repository;

import java.util.List;

import org.generation.lojinhaGames.model.Produto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProdutoRepository extends JpaRepository<Produto, Long>{

	List<Produto> findAllByDescricaoContainingIgnoreCase(String Descricao);
}
