package br.com.groovymusic.musica.validacao;

import br.com.groovymusic.config.ValidacaoException;

public class MusicaSemAlbumException extends ValidacaoException {

	public MusicaSemAlbumException() {
		super("Álbum da música não pode ser vazio");
	}
	
	public int getStatus() { return 400; }

	private static final long serialVersionUID = 1L;

}
