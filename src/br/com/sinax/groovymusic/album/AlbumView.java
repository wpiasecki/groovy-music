package br.com.sinax.groovymusic.album;

import java.util.List;
import java.util.stream.Collectors;

import br.com.sinax.groovymusic.artista.ArtistaView;
import br.com.sinax.groovymusic.musica.MusicaView;

public class AlbumView {

	public List<MusicaView> musicas;
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
		this.musicas = a.getMusicas().stream().map(MusicaView::new).collect(Collectors.toList());
	}
	
}
