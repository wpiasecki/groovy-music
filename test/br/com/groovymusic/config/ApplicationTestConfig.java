package br.com.groovymusic.config;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;

import br.com.groovymusic.album.AlbumResource;
import br.com.groovymusic.artista.ArtistaResource;
import br.com.groovymusic.musica.MusicaResource;

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
