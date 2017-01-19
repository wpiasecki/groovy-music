package br.com.groovymusic.musica;

import java.util.List;

import br.com.groovymusic.config.BaseService;
import br.com.groovymusic.config.EntityManagerImpl;

public class MusicaService extends BaseService<Musica> {

	public MusicaService(EntityManagerImpl emi) {
		super(emi);
	}
	
	public List<Musica> listar() {
		return getEm()
				.createNamedQuery("MusicaAll", Musica.class)
				.getResultList();
	}

}
