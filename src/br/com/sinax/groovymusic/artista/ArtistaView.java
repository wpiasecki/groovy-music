package br.com.sinax.groovymusic.artista;

public class ArtistaView {
	
	public Integer id;
	public String nome;

	public ArtistaView() {
	}
	
	public ArtistaView(Artista a) {
		this.id = a.getId();
		this.nome = a.getNome();
	}

	
}
