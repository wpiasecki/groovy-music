package br.com.groovymusic.album;

import java.util.List;

import org.junit.Assert;
import org.junit.Test;

import br.com.groovymusic.album.Album;
import br.com.groovymusic.album.AlbumService;
import br.com.groovymusic.artista.Artista;
import br.com.groovymusic.artista.ArtistaService;
import br.com.groovymusic.config.BaseTest;
import br.com.groovymusic.config.ambiente.AmbientePinkFloyd;

public class AlbumServiceTest extends BaseTest {

	@Test
	public void persistir() {
		ArtistaService artistaService = new ArtistaService(emt);
		AlbumService albumService = new AlbumService(emt);

		List<Album> result = emt.getEm().createNamedQuery("AlbumAll", Album.class).getResultList();
		Assert.assertEquals(0, result.size());

		Artista artista = new Artista("Pink Floyd");
		artistaService.salvar(artista);

		Album album = new Album("Wish You Were Here", 1975, artista);
		albumService.salvar(album);

		List<Album> result2 = emt.getEm().createNamedQuery("AlbumAll", Album.class).getResultList();
		Assert.assertEquals(1, result2.size());
		Album albumFetch = result2.get(0);
		
		Assert.assertEquals("Wish You Were Here", albumFetch.getNome());
		Assert.assertEquals("Pink Floyd", albumFetch.getArtista().getNome());
		Assert.assertNotNull(albumFetch.getId());
		Assert.assertNotNull(albumFetch.getArtista().getId());
	}
	
	
	@Test
	public void testAmbiente() {
		carregarAmbiente(AmbientePinkFloyd.class);
		
		List<Album> albums = emt.getEm().createNamedQuery("AlbumAll", Album.class).getResultList();
		Assert.assertEquals(1, albums.size());
		Album album = albums.get(0);
		
		Assert.assertEquals("Dark Side Of The Moon", album.getNome());
		Assert.assertEquals("Pink Floyd", album.getArtista().getNome());
	}

}
