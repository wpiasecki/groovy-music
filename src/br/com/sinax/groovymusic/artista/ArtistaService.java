package br.com.sinax.groovymusic.artista;

import br.com.sinax.groovymusic.config.BaseService;
import br.com.sinax.groovymusic.config.EntityManagerImpl;

public class ArtistaService extends BaseService<Artista> {

	public ArtistaService(EntityManagerImpl em) {
		super(em);
	}

}
