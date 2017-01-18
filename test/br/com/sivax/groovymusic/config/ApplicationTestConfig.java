package br.com.sivax.groovymusic.config;

import java.util.HashSet;
import java.util.Set;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;

import br.com.sinax.groovymusic.album.AlbumResource;

@ApplicationPath("/rest")
public class ApplicationTestConfig  extends Application {

	@Override
	public Set<Class<?>> getClasses() {
		Set<Class<?>> classes = new HashSet<>();
		classes.add(AlbumResource.class);
		return classes;
	}
	

}
