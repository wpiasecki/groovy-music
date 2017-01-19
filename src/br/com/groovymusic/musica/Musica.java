package br.com.groovymusic.musica;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.SequenceGenerator;

import br.com.groovymusic.album.Album;


@Entity
@NamedQueries({
	@NamedQuery(name="MusicaAll", query="SELECT m FROM Musica m ORDER BY m.nome, m.album.nome")
})
public class Musica {

	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="musica_sequence")
	@SequenceGenerator(name="musica_sequence", sequenceName="musica_sequence")
	private Integer id;
	private String nome;
	
	@ManyToOne
	private Album album;

	public Musica() {
	}
	
	public Musica(String nome) {
		this.nome = nome;
	}



	public Musica(String nome, Album album) {
		this.nome = nome;
		this.album = album;
	}
	

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Album getAlbum() {
		return album;
	}

	public String getNome() {
		return nome;
	}

	public void setAlbum(Album album) {
		this.album = album;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

}
