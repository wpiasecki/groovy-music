package br.com.sivax.groovymusic.album;

import java.util.List;

import org.junit.Assert;
import org.junit.Test;

import br.com.sinax.groovymusic.album.Album;
import br.com.sinax.groovymusic.album.AlbumService;
import br.com.sinax.groovymusic.artista.Artista;
import br.com.sinax.groovymusic.artista.ArtistaService;
import br.com.sivax.groovymusic.config.BaseTest;

public class AlbumDbTest extends BaseTest {

	@Test
	public void persistir() {
		ArtistaService artistaService = new ArtistaService();
		artistaService.setEm(em);

		AlbumService albumService = new AlbumService();
		albumService.setEm(em);

		List<Album> result = em.createNamedQuery("AlbumAll", Album.class).getResultList();
		Assert.assertEquals(0, result.size());

		Artista artista = new Artista("Pink Floyd");
		artistaService.salvar(artista);

		Album album = new Album("Wish You Were Here", 1975, artista);
		albumService.salvar(album);

		List<Album> result2 = em.createNamedQuery("AlbumAll", Album.class).getResultList();
		Assert.assertEquals(1, result2.size());
		Album albumFetch = result2.get(0);
		
		Assert.assertEquals("Wish You Were Here", albumFetch.getNome());
		Assert.assertEquals("Pink Floyd", albumFetch.getArtista().getNome());
		Assert.assertNotNull(albumFetch.getId());
		Assert.assertNotNull(albumFetch.getArtista().getId());
	}

}
