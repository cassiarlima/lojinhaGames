package org.generation.lojinhaGames.repository;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

import org.generation.lojinhaGames.model.Usuario;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class UsuarioRepositoryTest {
	@Autowired
	private UsuarioRepository usuarioRepository;

	@BeforeAll
	void start() {
		LocalDate data = LocalDate.parse("2000-07-22", DateTimeFormatter.ofPattern("yyyy-MM-dd"));
		Usuario usuario = new Usuario(0, "JoÃ£o da Silva", "joao@email.com.br", "13465278");
		if (!usuarioRepository.findByUsuario(usuario.getUsuario()).isPresent())
			usuarioRepository.save(usuario);
		usuario = new Usuario(0, "Manuel da Silva", "manuel@email.com.br", "13465278");
		if (!usuarioRepository.findByUsuario(usuario.getUsuario()).isPresent())
			usuarioRepository.save(usuario);
		usuario = new Usuario(0, "Frederico da Silva", "frederico@email.com.br", "13465278");
		if (!usuarioRepository.findByUsuario(usuario.getUsuario()).isPresent())
			usuarioRepository.save(usuario);
		usuario = new Usuario(0, "Paulo Antunes", "paulo@email.com.br", "13465278");
		if (!usuarioRepository.findByUsuario(usuario.getUsuario()).isPresent())
			usuarioRepository.save(usuario);
	}

	@Test
	@DisplayName("ðŸ’¾ Retorna o nome")
	public void findByNomeRetornaNome() {
		Usuario usuario = usuarioRepository.findByNome("JoÃ£o da Silva");
		assertTrue(usuario.getNome().equals("JoÃ£o da Silva"));
	}

	@Test
	@DisplayName("ðŸ’¾ Retorna 3 usuarios")
	public void findAllByNomeContainingIgnoreCaseRetornaTresUsuarios() {
		List<Usuario> listaDeUsuarios = usuarioRepository.findAllByNomeContainingIgnoreCase("Silva");
		assertEquals(3, listaDeUsuarios.size());
	}

	@AfterAll
	public void end() {
		System.out.println("Teste Finalizado!");
	}
}
