package br.com.groovymusic.config.ambiente;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import br.com.groovymusic.album.Album;
import br.com.groovymusic.musica.Musica;

public class AmbientePinkFloyd3Musicas implements Ambiente {

	@Override
	public List<Object> getEntidades() {
		List<Object> entidades = new ArrayList<>(new AmbientePinkFloyd().getEntidades());
		Album album = (Album) entidades.stream().filter(it -> it instanceof Album).findFirst().get();
		entidades.addAll(Arrays.asList(new Musica("Money", album), new Musica("Time", album)));
		return entidades;
	}
	
}
