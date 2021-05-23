package br.com.caelum.leilao.controllers;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;

import org.junit.Test;

import br.com.caelum.leilao.dominio.Usuario;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.path.xml.XmlPath;

public class UsuarioControllerTest {

	@Test
	public void deveRetornarListaDeUsuarios() {
		XmlPath path = given().header("Accept", "application/xml").get("/usuarios").andReturn().xmlPath();

		Usuario usuario1 = path.getObject("list.usuario[0]", Usuario.class);
		Usuario usuario2 = path.getObject("list.usuario[0]", Usuario.class);

		Usuario usuarioEsperado1 = new Usuario(1, "Paulo Henrique", "paulo@henrique.com");
		Usuario usuarioEsperado2 = new Usuario(2, "José Eduardo	", "jose@eduardo.com");

		assertEquals(usuarioEsperado1, usuario1);
		assertEquals(usuarioEsperado2, usuario2);
	}

	@Test
	public void deveRetornarUsuarioPeloId() {
		JsonPath path = given().header("Accept", "application/json").param("usuario.id", 1).get("/usuarios/show")
				.andReturn().jsonPath();

		Usuario usuario = path.getObject("usuario", Usuario.class);
		Usuario usuarioEsperado = new Usuario(1, "Paulo Henrique", "paulo@henrique.com");

		assertEquals(usuarioEsperado, usuario);
	}

	@Test
	public void deveAdicionarUmUsuario() {
		Usuario joao = new Usuario("Joao da Silva", "joao@dasilva.com");

		XmlPath path = given().accept(ContentType.XML).contentType(ContentType.XML).body(joao).expect().statusCode(200)
				.when().post("/usuarios").andReturn().xmlPath();

		Usuario usuarioEsperado = path.getObject("usuario", Usuario.class);

		assertEquals("Joao da Silva", usuarioEsperado.getNome());
		assertEquals("joao@dasilva.com", usuarioEsperado.getEmail());
	}
}
