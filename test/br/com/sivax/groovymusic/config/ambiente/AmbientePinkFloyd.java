package br.com.sivax.groovymusic.config.ambiente;

import java.util.Arrays;
import java.util.List;

import br.com.sinax.groovymusic.album.Album;
import br.com.sinax.groovymusic.artista.Artista;
import br.com.sinax.groovymusic.musica.Musica;

public class AmbientePinkFloyd implements Ambiente{

	@Override
	public List<Object> getEntidades() {
		Artista ar = new Artista("Pink Floyd");
		Album al = new Album("Dark Side Of The Moon", 1973, ar);
		Musica m = new Musica("Brain Damage", al);
		
		return Arrays.asList(ar, al, m);
	}
	
}
