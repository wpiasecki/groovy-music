package br.com.groovymusic.artista;

import java.util.List;

import br.com.groovymusic.config.BaseService;
import br.com.groovymusic.config.EntityManagerImpl;

public class ArtistaService extends BaseService<Artista> {

	public ArtistaService(EntityManagerImpl em) {
		super(em);
	}

	public List<Artista> listar() {
		return getEm()
				.createNamedQuery("ArtistaAll", Artista.class)
				.getResultList();
	}
}
