package org.generation.lojinhaGames.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import org.generation.lojinhaGames.model.Usuario;
import org.generation.lojinhaGames.repository.UsuarioRepository;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class UsuarioControllerTest {
	
	@Autowired
	private TestRestTemplate testRestTemplate;
	private Usuario usuario;
	private Usuario usuarioUpdate;
	private Usuario usuarioAdmin;
	@Autowired
	private UsuarioRepository usuarioRepository;

	@BeforeAll
	public void start(){
	usuarioAdmin = new Usuario (0L, "Administrador","admin@email.com.br", "admin123");
	if(!usuarioRepository.findByUsuario(usuarioAdmin.getUsuario()).isPresent()) {
		HttpEntity <Usuario> request = new HttpEntity<Usuario>(usuarioAdmin);
		testRestTemplate
		.exchange("/usuarios/cadastrar", HttpMethod.POST, request, Usuario.class);
	
	}

	LocalDate dataPost = LocalDate.parse("2000-07-22",
	DateTimeFormatter.ofPattern("yyyy-MM-dd"));
	usuario=new Usuario(0L,"Paulo Antunes","paulo@email.com.br","13465278");
	LocalDate dataPut = LocalDate.parse("2000-07-22",
	DateTimeFormatter.ofPattern("yyyy-MM-dd"));
	usuarioUpdate=new Usuario(2L,"Paulo Antunes de Souza","paulo_souza@email.com.br","souza123");
			
	}

	@Test
	@Order(1)
	@DisplayName("âœ” Cadastrar UsuÃ¡rio!")
	public void deveRealizarPostUsuario() {
		HttpEntity<Usuario> request = new HttpEntity<Usuario>(usuario);
		ResponseEntity<Usuario> resposta = testRestTemplate.exchange("/usuarios/cadastrar", HttpMethod.POST, request,
				Usuario.class);
		assertEquals(HttpStatus.CREATED, resposta.getStatusCode());
	}

	@Test
	@Order(2)
	@DisplayName("ðŸ‘� Listar todos os UsuÃ¡rios!")
	public void deveMostrarTodosUsuarios() {
		ResponseEntity<String> resposta = testRestTemplate.withBasicAuth("admin@email.com.br", "admin123")
				.exchange("/usuarios/all", HttpMethod.GET, null, String.class);
		assertEquals(HttpStatus.OK, resposta.getStatusCode());
	}

	@Test
	@Order(3)
	@DisplayName("ðŸ˜³ Alterar UsuÃ¡rio!")
	public void deveRealizarPutUsuario() {
		HttpEntity<Usuario> request = new HttpEntity<Usuario>(usuarioUpdate);
		ResponseEntity<Usuario> resposta = testRestTemplate.withBasicAuth("admin@email.com.br", "admin123")
				.exchange("/usuarios/alterar", HttpMethod.PUT, request, Usuario.class);
		assertEquals(HttpStatus.OK, resposta.getStatusCode());
	}
}


