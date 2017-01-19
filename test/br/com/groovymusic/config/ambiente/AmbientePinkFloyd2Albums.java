package br.com.groovymusic.config.ambiente;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import br.com.groovymusic.album.Album;
import br.com.groovymusic.artista.Artista;
import br.com.groovymusic.musica.Musica;

public class AmbientePinkFloyd2Albums implements Ambiente {

	@Override
	public List<Object> getEntidades() {
		List<Object> entidades = new ArrayList<>(new AmbientePinkFloyd().getEntidades());
		Artista ar = (Artista) entidades.stream().filter(it -> it instanceof Artista).findFirst().get();
		Album al = new Album("Delicate Sound of Thunder", 1973, ar);
		entidades.add(al);

		entidades.addAll(Arrays.asList("Learning to Fly", "Shine On You Crazy Diamond", "Sorrow")
				.stream()
				.map(m -> new Musica(m, al) )
				.collect(Collectors.toList()));

		return entidades;
	}

}
