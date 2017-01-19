package br.com.groovymusic.config;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class EntityManagerProducao implements EntityManagerImpl {

	private static EntityManagerFactory emf = Persistence.createEntityManagerFactory("groovy-music");

	@Override
	public EntityManager getEm() {
		return emf.createEntityManager();
	}

}
