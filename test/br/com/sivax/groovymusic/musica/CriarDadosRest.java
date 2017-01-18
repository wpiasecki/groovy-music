package br.com.sivax.groovymusic.musica;

import java.util.List;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.Invocation.Builder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.junit.Assert;

import br.com.sinax.groovymusic.album.AlbumView;
import br.com.sinax.groovymusic.artista.ArtistaView;
import br.com.sinax.groovymusic.musica.MusicaView;

public class CriarDadosRest {
	
	public Builder clientBuilder(String path) {
		return target(path).request();
	}
	
	public WebTarget target(String path) {
		Client client = ClientBuilder.newClient();
		return client.target("http://localhost:8080/groovy-music/rest" + path);
	}

	void postAndAssert(String path, Object object) {
		Response post = clientBuilder(path).post(Entity.entity(object, MediaType.APPLICATION_JSON));
		Assert.assertEquals(200, post.getStatus());
	}
	
	public void criarArtista() {
		ArtistaView artista = new ArtistaView();
		artista.nome = "Pink Floyd";
		postAndAssert("/artista", artista);
	}
	
	public void criarAlbum() {
		AlbumView album = new AlbumView();
		album.nome = "Dark Side Of The Moon";
		album.ano = 1973;
		album.artista = new ArtistaView(1);
		postAndAssert("/album", album);
	}
	
	public void criarMusica() {
		List<AlbumView> albums = clientBuilder("/album").get(new GenericType<List<AlbumView>>(){});
		
		MusicaView musica = new MusicaView();
		musica.nome = "Time";
		musica.album = albums.get(0);
		postAndAssert("/musica", musica);
		
		MusicaView musica2 = new MusicaView();
		musica2.nome = "Us and Them";
		musica2.album = albums.get(0);
		postAndAssert("/musica", musica2);
		
		List<MusicaView> musicas = clientBuilder("/musica").get(new GenericType<List<MusicaView>>(){});
		Assert.assertEquals(2, musicas.size());
	}
	
	public static void main(String[] args) {
		CriarDadosRest rest = new CriarDadosRest();
		rest.criarArtista();
		rest.criarAlbum();
		rest.criarMusica();
	}
	
}
