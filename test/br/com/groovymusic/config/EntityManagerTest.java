package br.com.groovymusic.config;

import javax.persistence.EntityManager;
import javax.persistence.Persistence;

import br.com.groovymusic.config.EntityManagerImpl;

public class EntityManagerTest implements EntityManagerImpl {

	private static EntityManager em;
	
	public void recreate() {
		em = Persistence.createEntityManagerFactory("groovy-music-test").createEntityManager();
	}
	
	@Override
	public EntityManager getEm() {
		if (em == null) recreate();
		return em;
	}

}
