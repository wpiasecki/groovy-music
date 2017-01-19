package br.com.groovymusic.musica;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.BiConsumer;
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
		
		BiConsumer<String, Integer> criaAlbum = (nome, ano) -> {
			AlbumView album = new AlbumView();
			album.nome = nome;
			album.ano = ano;
			album.artista = new ArtistaView(1);
			postAndAssert("/album", album);
		};
		
		criaAlbum.accept("Delicate Sound of Thunder", 1988);
		criaAlbum.accept("Dark Side Of The Moon", 1973);
		criaAlbum.accept("The Division Bell", 1994);
		
	}
	
	public void criarMusica() {
		List<AlbumView> albums = clientBuilder("/album").get(new GenericType<List<AlbumView>>(){});
		
		BiConsumer<String, AlbumView> criaMusica = (nome, album) -> {
			MusicaView musica = new MusicaView();
			musica.nome = nome;
			musica.album = album;
			postAndAssert("/musica", musica);
		};
		
		Consumer<String> criaMusicaDarkSide = nome -> criaMusica.accept(nome, albums.get(0));
		Consumer<String> criaMusicaDelicateSoundOfThunder = nome -> criaMusica.accept(nome, albums.get(1));
		Consumer<String> criaMusicaDivisionBell = nome -> criaMusica.accept(nome, albums.get(2));
		
		Map<Consumer<String>, List<String>> musicas = new HashMap<>();
		
		musicas.put(criaMusicaDarkSide, Arrays.asList(
				"Time", 
				"Money",
				"Us and Them"));
		
		musicas.put(criaMusicaDivisionBell, Arrays.asList(
				"Coming Back To Life", 
				"Marooned", 
				"High Hopes"));
		
		musicas.put(criaMusicaDelicateSoundOfThunder, Arrays.asList(
				"Shine On You Crazy Diamond",
				"Learning to Fly",
				"Sorrow"));
		
		musicas.forEach((consumer, list) -> list.forEach(consumer));
		
		List<MusicaView> musicasRest = clientBuilder("/musica").get(new GenericType<List<MusicaView>>(){});
		Assert.assertEquals(musicasRest.size(), musicas.values().stream().mapToInt(a -> a.size()).sum());
	}
	
	public static void main(String[] args) {
		CriarDadosRest rest = new CriarDadosRest();
		rest.criarArtista();
		rest.criarAlbum();
		rest.criarMusica();
	}
	
}
