package br.com.sinax.groovymusic.musica;

public class MusicaView {

	public String nome;
	
	public MusicaView() {
	}
	
	public MusicaView(Musica m) {
		this.nome = m.getNome();
	}
	
}
