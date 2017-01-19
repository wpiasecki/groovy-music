package br.com.groovymusic.album;

import java.util.List;

import javax.ws.rs.client.Entity;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.MediaType;

import org.junit.Assert;
import org.junit.Test;

import br.com.groovymusic.album.AlbumView;
import br.com.groovymusic.artista.ArtistaView;
import br.com.groovymusic.config.BaseTest;
import br.com.groovymusic.config.ambiente.AmbientePinkFloyd;

public class AlbumResourceTest extends BaseTest {

	@Test
	public void listar() {
		carregarAmbiente(AmbientePinkFloyd.class);
		
		List<AlbumView> albums = clientBuilder("/album").get(new GenericType<List<AlbumView>>(){});

		AlbumView album = albums.get(0);

		Assert.assertEquals((Integer) 1973, album.ano);
		Assert.assertEquals("Dark Side Of The Moon", album.nome);
	}
	
	@Test
	public void salvar() {
		carregarAmbiente(AmbientePinkFloyd.class);
		
		AlbumView album = new AlbumView();
		album.ano = 1994;
		album.nome = "The Division Bell";
		album.artista = new ArtistaView(1);
		
		clientBuilder("/album").post( Entity.entity(album, MediaType.APPLICATION_JSON ));
		List<AlbumView> albums = clientBuilder("/album").get(new GenericType<List<AlbumView>>(){});
		Assert.assertEquals(2,  albums.size());
	}
		
}
