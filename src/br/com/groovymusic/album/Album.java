package br.com.groovymusic.album;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;

import br.com.groovymusic.artista.Artista;
import br.com.groovymusic.musica.Musica;

@Entity
@NamedQueries({ @NamedQuery(name = "AlbumAll", query = "SELECT a FROM Album a ORDER BY a.ano") })
public class Album {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "album_sequence")
	@SequenceGenerator(name = "album_sequence", sequenceName = "album_sequence")
	private Integer id;
	private String nome;
	private Integer ano;
	@ManyToOne(optional = false)
	private Artista artista;
	@OneToMany
	private List<Musica> musicas;

	public Album() {
	}

	public Album(String nome, Integer ano, Artista artista) {
		this.nome = nome;
		this.ano = ano;
		this.artista = artista;
	}

	public Album(Integer albumId) {
		this.id = albumId;
	}

	public Album(Integer id, String nome, Integer ano, Artista artista) {
		this.id = id;
		this.nome = nome;
		this.ano = ano;
		this.artista = artista;
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

	public List<Musica> getMusicas() {
		return musicas;
	}

	public void setMusicas(List<Musica> musicas) {
		this.musicas = musicas;
	}

	public Integer getAno() {
		return ano;
	}

	public void setAno(Integer ano) {
		this.ano = ano;
	}

	public Artista getArtista() {
		return artista;
	}

	public void setArtista(Artista artista) {
		this.artista = artista;
	}

}
