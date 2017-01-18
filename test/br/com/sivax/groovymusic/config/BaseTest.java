package br.com.sivax.groovymusic.config;

import javax.persistence.EntityManager;
import javax.persistence.Persistence;

import org.jboss.resteasy.plugins.server.undertow.UndertowJaxrsServer;
import org.junit.BeforeClass;

public class BaseTest {

	protected EntityManager em = Persistence.createEntityManagerFactory("groovy-music-test").createEntityManager();
	protected static UndertowJaxrsServer server = new UndertowJaxrsServer();
	
	@BeforeClass
	public static void beforeClass() {
		server.start();
		server.deploy(ApplicationTestConfig.class);
	}
	
}
