package br.com.groovymusic.config;

public class Validador {

	public Validador se(Boolean condicao, Class<? extends ValidacaoException> erro) {
		if (condicao) {
			try {
				throw erro.newInstance();
			} catch (InstantiationException | IllegalAccessException e) {
				throw new RuntimeException(e);
			}
		}
		return this;
	}

}
