package br.com.sinax.groovymusic.config;

import javax.persistence.EntityManager;
import javax.persistence.Persistence;

public class EntityManagerProducao implements EntityManagerImpl {

	@Override
	public EntityManager getEm() {
		return Persistence.createEntityManagerFactory("groovy-music").createEntityManager();
	}

}
