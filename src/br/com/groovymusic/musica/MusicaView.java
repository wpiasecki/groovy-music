package br.com.groovymusic.musica;

import br.com.groovymusic.album.AlbumView;

public class MusicaView {

	public Integer id;
	public String nome;
	public AlbumView album;
	
	public MusicaView() {
	}
	
	public MusicaView(Musica m) {
		this.id = m.getId();
		this.nome = m.getNome();
		this.album = new AlbumView(m.getAlbum());
	}
	
	public Musica converterParaEntidade() {
		return new Musica(nome, album.converterParaEntidade());
	}
	
	public Musica atualizarEntidade(Musica m) {
		m.setNome(nome);
		return m;
	}
	
}
