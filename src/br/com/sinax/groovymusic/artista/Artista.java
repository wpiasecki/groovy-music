package br.com.sinax.groovymusic.artista;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;

import br.com.sinax.groovymusic.album.Album;

@Entity
public class Artista {

	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="artista_sequence")
	@SequenceGenerator(name="artista_sequence", sequenceName="artista_sequence")
	private Integer id;
	private String nome;
	@OneToMany
	private List<Album> albums;

	public Artista() {
	}
	
	public Artista(String nome) {
		this.nome = nome;
	}

	
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public List<Album> getAlbums() {
		return albums;
	}

	public void setAlbums(List<Album> albums) {
		this.albums = albums;
	}

}
