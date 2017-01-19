package br.com.groovymusic.musica;

import java.util.List;

import javax.ws.rs.client.Entity;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.junit.Assert;
import org.junit.Test;

import br.com.groovymusic.album.AlbumView;
import br.com.groovymusic.config.BaseTest;
import br.com.groovymusic.config.ambiente.AmbientePinkFloyd;
import br.com.groovymusic.musica.MusicaView;

public class MusicaResourceTest extends BaseTest {

	@Test
	public void listar() {
		carregarAmbiente(AmbientePinkFloyd.class);
		
		List<MusicaView> musicas = clientBuilder("/musica").get(new GenericType<List<MusicaView>>(){});
		
		Assert.assertEquals(1, musicas.size());
		
		MusicaView musica = musicas.get(0);
		
		Assert.assertEquals("Brain Damage", musica.nome);
	}
	
	@Test
	public void salvar() {
		carregarAmbiente(AmbientePinkFloyd.class);
		
		List<AlbumView> albums = clientBuilder("/album").get(new GenericType<List<AlbumView>>(){});
		
		MusicaView musica = new MusicaView();
		musica.nome = "Time";
		musica.album = albums.get(0);
		
		Response post = clientBuilder("/musica").post(Entity.entity(musica, MediaType.APPLICATION_JSON));
		
		Assert.assertEquals(200, post.getStatus());
		
		List<MusicaView> musicas2 = clientBuilder("/musica").get(new GenericType<List<MusicaView>>(){});
		
		Assert.assertEquals(2, musicas2.size());
	}
	
	@Test
	public void excluir() {
		carregarAmbiente(AmbientePinkFloyd.class);
		
		List<MusicaView> musicas = clientBuilder("/musica").get(new GenericType<List<MusicaView>>(){});
		Assert.assertEquals(1, musicas.size());
		
		Response delete = target("/musica").path(musicas.get(0).id.toString()).request().delete();
		
		Assert.assertEquals(204, delete.getStatus());
		
		List<MusicaView> musicas2 = clientBuilder("/musica").get(new GenericType<List<MusicaView>>(){});
		
		Assert.assertEquals(0, musicas2.size());
	}
	
	@Test
	public void update() {
		carregarAmbiente(AmbientePinkFloyd.class);
		
		List<MusicaView> musicas = clientBuilder("/musica").get(new GenericType<List<MusicaView>>(){});
		
		MusicaView m = musicas.get(0);
		Assert.assertEquals("Brain Damage", m.nome);
		m.nome = "Money";
		
		Response put = target("/musica")
				.path(m.id.toString())
				.request()
				.put(Entity.entity(m, MediaType.APPLICATION_JSON));
		
		Assert.assertEquals(200, put.getStatus());
		
		List<MusicaView> musicas2 = clientBuilder("/musica").get(new GenericType<List<MusicaView>>(){});
		
		Assert.assertEquals("Money", musicas2.get(0).nome);
	}
	
}
