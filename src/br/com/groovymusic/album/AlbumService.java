package br.com.groovymusic.album;

import java.util.List;

import br.com.groovymusic.config.BaseService;
import br.com.groovymusic.config.EntityManagerImpl;

public class AlbumService extends BaseService<Album> {

	public AlbumService(EntityManagerImpl em) {
		super(em);
	}

	public  List<Album> listar() {
		return getEm().createNamedQuery("AlbumAll", Album.class).getResultList();
	}

	
	
}
