package org.generation.lojinhaGames.repository;

import java.util.List;

import org.generation.lojinhaGames.model.Categoria;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoriaRepository extends JpaRepository <Categoria, Long>{
	List<Categoria> findAllByNomeJogoContainingIgnoreCase (String NomeJogo);

	List<Categoria> findAllByDescricaoContainingIgnoreCase (String descricao);
	
	
}
