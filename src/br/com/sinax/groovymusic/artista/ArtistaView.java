package br.com.sinax.groovymusic.artista;

public class ArtistaView {
	
	public Integer id;
	public String nome;

	public ArtistaView(Integer id, String nome) {
		this.id = id;
		this.nome = nome;
	}

	public ArtistaView() {
	}
	
	public ArtistaView(Artista a) {
		this.id = a.getId();
		this.nome = a.getNome();
	}

	public ArtistaView(int id) {
		this.id = id;
	}

	public Artista converterParaEntidade() {
		return new Artista(id, nome);
	}

	
}
