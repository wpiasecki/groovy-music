package br.com.sivax.groovymusic.album;

import java.util.List;

import javax.ws.rs.client.Entity;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.MediaType;

import org.junit.Assert;
import org.junit.Test;

import br.com.sinax.groovymusic.album.Album;
import br.com.sinax.groovymusic.album.AlbumView;
import br.com.sinax.groovymusic.artista.ArtistaView;
import br.com.sivax.groovymusic.config.BaseTest;
import br.com.sivax.groovymusic.config.ambiente.AmbientePinkFloyd;

public class AlbumResourceTest extends BaseTest {

	@Test
	public void listar() {
		carregarAmbiente(AmbientePinkFloyd.class);
		
		{
			List<Album> albums = emt.getEm().createNamedQuery("AlbumAll", Album.class).getResultList();
			Assert.assertEquals(1, albums.size());
			Album album = albums.get(0);
			
			Assert.assertEquals("Dark Side Of The Moon", album.getNome());
			Assert.assertEquals("Pink Floyd", album.getArtista().getNome());
		}
		
		
		List<AlbumView> albums = clientBuilder("/album/")
				.get(new GenericType<List<AlbumView>>(){});

		AlbumView album = albums.get(0);

		Assert.assertEquals((Integer) 1973, album.ano);
		Assert.assertEquals("Dark Side Of The Moon", album.nome);
	}
	
	@Test
	public void salvar() {
		AlbumView album = new AlbumView();
		album.ano = 1994;
		album.nome = "The Division Bell";
		album.artista = new ArtistaView();
		clientBuilder("/album/").post( Entity.entity(album, MediaType.APPLICATION_JSON ));
		
	}
		
}
