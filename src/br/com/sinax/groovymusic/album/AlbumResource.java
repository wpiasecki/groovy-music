package br.com.sinax.groovymusic.album;

import java.util.Arrays;
import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import br.com.sinax.groovymusic.artista.Artista;
import br.com.sinax.groovymusic.musica.Musica;

@Path("/album")
public class AlbumResource {

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<AlbumView> listar() {
		Artista pinkFloyd = new Artista("Pink Floyd");
		Album darkSide = new Album("Dark side of the moon", 1973, pinkFloyd);

		List<Musica> musicas = Arrays.asList(new Musica("Brain Damage", darkSide));

		pinkFloyd.setAlbums(Arrays.asList(darkSide));
		darkSide.setMusicas(musicas);
		
		return Arrays.asList(new AlbumView(darkSide));
	}

}
