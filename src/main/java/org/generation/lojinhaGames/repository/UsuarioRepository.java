package org.generation.lojinhaGames.repository;

import java.util.List;
import java.util.Optional;

import org.generation.lojinhaGames.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsuarioRepository extends JpaRepository <Usuario, Long>{
	public Optional <Usuario> findByUsuario(String Usuario);

	public Usuario findByNome(String string);

	public List<Usuario> findAllByNomeContainingIgnoreCase(String string);
}
