package br.com.groovymusic.album;

import br.com.groovymusic.artista.ArtistaView;

public class AlbumView {

	public Integer id;
	public String nome;
	public Integer ano;
	public ArtistaView artista;
	
	public AlbumView() {
	}
	
	public AlbumView(Album a) {
		this.id = a.getId();
		this.nome = a.getNome();
		this.ano = a.getAno();
		this.artista = new ArtistaView(a.getArtista());
	}

	public Album converterParaEntidade() {
		return new Album(id, nome, ano, artista.converterParaEntidade());
	}
	
}
