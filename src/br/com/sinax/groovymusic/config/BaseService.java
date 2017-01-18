package br.com.sinax.groovymusic.config;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

public class BaseService<T> {

	private EntityManager em = Persistence.createEntityManagerFactory("groovy-music").createEntityManager();

	public void salvar(T t) {
		withTransaction(() -> { em.persist(t); });
	}

	public void withTransaction(Runnable r) {
		EntityTransaction t = null;
		try {
			t = em.getTransaction();
			t.begin();
			r.run();
			t.commit();
		} catch (Exception e) {
			if (t != null) t.rollback();
			throw new RuntimeException(e);
		}
	}

	public EntityManager getEm() {
		return em;
	}

	public void setEm(EntityManager em) {
		this.em = em;
	}

}
