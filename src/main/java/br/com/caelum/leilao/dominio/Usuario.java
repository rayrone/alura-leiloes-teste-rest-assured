package br.com.caelum.leilao.dominio;

public class Usuario {

	private int id;

	private String nome;

	private String email;

	public Usuario(String nome, String email) {
		this.nome = nome;
		this.email = email;
	}

	public Usuario(int id, String nome, String email) {
		super();
		this.id = id;
		this.nome = nome;
		this.email = email;
	}

	public int getId() {
		return id;
	}

	public String getNome() {
		return nome;
	}

	public String getEmail() {
		return email;
	}
}
