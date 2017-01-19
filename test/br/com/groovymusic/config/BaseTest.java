package br.com.groovymusic.config;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Invocation.Builder;
import javax.ws.rs.client.WebTarget;

import org.jboss.resteasy.plugins.server.undertow.UndertowJaxrsServer;
import org.jboss.resteasy.test.TestPortProvider;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.BeforeClass;

import com.google.inject.Guice;

import br.com.groovymusic.config.BaseService;
import br.com.groovymusic.config.DI;
import br.com.groovymusic.config.ambiente.Ambiente;
public class BaseTest {

	protected EntityManagerTest emt = new EntityManagerTest();
	protected static UndertowJaxrsServer server = new UndertowJaxrsServer();
	
	@BeforeClass
	public static void beforeClass() {
		DI.TEST = true;
		server.start();
		server.deploy(ApplicationTestConfig.class);
		Guice.createInjector(new DITest());
	}
	
	@AfterClass
	public static void afterClass() {
		server.stop();
	}
	
	@After
	public void after() {
		emt.recreate();
	}
	
	public Builder clientBuilder(String path) {
		return target(path).request();
	}
	
	public WebTarget target(String path) {
		Client client = ClientBuilder.newClient();
		return client.target(TestPortProvider.generateURL("/rest" + path));
	}
	
	protected void carregarAmbiente(Class<? extends Ambiente> clazzAmbiente) {
		BaseService<Object> service = new BaseService<>(emt);
		try {
			service.salvarBatch( clazzAmbiente.newInstance().getEntidades() );
		} catch (InstantiationException | IllegalAccessException e) {
			throw new RuntimeException(e);
		}
	}
}
