package br.com.sivax.groovymusic.config;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;

import br.com.sinax.groovymusic.album.AlbumResource;
import br.com.sinax.groovymusic.artista.ArtistaResource;
import br.com.sinax.groovymusic.musica.MusicaResource;

@ApplicationPath("/rest")
public class ApplicationTestConfig  extends Application {

	@Override
	public Set<Class<?>> getClasses() {
		return new HashSet<>(Arrays.asList(
				AlbumResource.class, 
				MusicaResource.class, 
				ArtistaResource.class));
	}
	

}
