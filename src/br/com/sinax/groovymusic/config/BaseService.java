package br.com.sinax.groovymusic.config;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

import com.google.inject.Inject;

public class BaseService<T> {

	private EntityManager em;
	
	@Inject
	public BaseService(EntityManagerImpl emi) {
		this.em = emi.getEm();
	}
	
	
	public void salvar(T t) {
		comTransacao(() -> { em.persist(t); });
	}
	

	public void salvarBatch(List<T> ts) {
		comTransacao(() -> {
			for (T t : ts) {
				em.persist(t);
			}
		});
	}

	public void comTransacao(Runnable r) {
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
