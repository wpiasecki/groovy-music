package br.com.groovymusic.musica.validacao;

import br.com.groovymusic.config.ValidacaoException;

public class MusicaDuplicadaException extends ValidacaoException {

	public MusicaDuplicadaException() {
		super("Música já existe no álbum");
	}
	
	public int getStatus() { return 409; }

	private static final long serialVersionUID = 1L;

}
