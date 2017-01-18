package br.com.sivax.groovymusic.album;

import java.util.List;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.core.GenericType;

import org.jboss.resteasy.test.TestPortProvider;
import org.junit.Assert;
import org.junit.Test;

import br.com.sinax.groovymusic.album.AlbumView;
import br.com.sivax.groovymusic.config.BaseTest;

public class AlbumResourceTest extends BaseTest {

	@Test
	public void listar() {
		
		Client client = ClientBuilder.newClient();
		List<AlbumView> albums = client.target(TestPortProvider.generateURL("/rest/album/"))
				.request()
				.get(new GenericType<List<AlbumView>>(){});

		AlbumView album = albums.get(0);

		Assert.assertEquals(album.ano, (Integer) 1973);
	}
	
}
