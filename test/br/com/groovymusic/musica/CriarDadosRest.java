package br.com.groovymusic.musica;

import java.util.Arrays;
import java.util.List;
import java.util.function.Consumer;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.Invocation.Builder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.junit.Assert;

import br.com.groovymusic.album.AlbumView;
import br.com.groovymusic.artista.ArtistaView;
import br.com.groovymusic.musica.MusicaView;

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
		
		Consumer<String> criaMusica = nome -> {
			MusicaView musica = new MusicaView();
			musica.nome = nome;
			musica.album = albums.get(0);
			postAndAssert("/musica", musica);
		};
		
		List<String> musicas = Arrays.asList(
				"Speak to me", 
				"The Great Gig in the Sky", 
				"Time", 
				"Any Colour You Like",
				"Money",
				"Us and Them", 
				"Breathe", 
				"Eclipse");
		musicas.forEach(criaMusica);
		
		List<MusicaView> musicasCriadas = clientBuilder("/musica").get(new GenericType<List<MusicaView>>(){});
		Assert.assertEquals(musicas.size(), musicasCriadas.size());
	}
	
	public static void main(String[] args) {
		CriarDadosRest rest = new CriarDadosRest();
		rest.criarArtista();
		rest.criarAlbum();
		rest.criarMusica();
	}
	
}
