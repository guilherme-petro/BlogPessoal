package org.generation.blogpessoal.repository;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;
import java.util.Optional;

import org.generation.blogpessoal.model.Usuario;
import org.generation.blogpessoal.service.UsuarioService;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@TestInstance(TestInstance.Lifecycle.PER_CLASS) //Indica os resultados dos teste por cada classe, em vez de uma única vez
public class UsuarioRepositoryTest {

	@Autowired
	private UsuarioService usuarioService;
	
	@Autowired
	private UsuarioRepository usuarioRepository;
	
	@BeforeAll
	void start() {
		usuarioRepository.save(new Usuario(0L, "Ramon Daniel Santos", "ramonzito@gmail.com", "1223456789"));
		usuarioRepository.save(new Usuario(0L, "Marcos Santos Sartorio", "marcsart@hotmail.com", "senhamarcos"));
		usuarioRepository.save(new Usuario(0L, "Giovanna Pasqual Santos", "giovannateste", "Senha!G1"));
	}
	
	@Test //Resultados: Pass (certo), Fail (não retorna o resultado esperado) e Error (erro de sintaxe)
	@DisplayName("Retorna apenas um usuário")
	public void retornarUsuario() {
		
		Optional<Usuario> usuario = usuarioRepository.findByUsuario("giovannateste");
		assertTrue(usuario.get().getUsuario().equals("giovannateste"));

	}
	
	
	@Test
	@DisplayName("Retorna três usuários")
	public void retornarTresUsuarios() {
		
		List<Usuario> listaUsuarios = usuarioRepository.findAllByNomeContainingIgnoreCase("santos");
		assertEquals(3, listaUsuarios.size());
		
		assertTrue(listaUsuarios.get(0).getNome().equals("Ramon Daniel Santos"));
		assertTrue(listaUsuarios.get(1).getNome().equals("Marcos Santos Sartorio"));
		assertTrue(listaUsuarios.get(2).getNome().equals("Giovanna Pasqual Santos"));
		
	}

	@AfterAll
	public void end() {
		usuarioRepository.deleteAll();
	}
	
	
}
