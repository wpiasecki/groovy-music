package br.com.groovymusic.config;

public abstract class ValidacaoException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public ValidacaoException(String erro) {
		super(erro);
	}
	
	public abstract int getStatus();
	
}
