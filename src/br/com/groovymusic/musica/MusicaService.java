package br.com.groovymusic.musica;

import java.util.List;

import br.com.groovymusic.config.BaseService;
import br.com.groovymusic.config.EntityManagerImpl;
import br.com.groovymusic.config.Validador;
import br.com.groovymusic.musica.validacao.MusicaDuplicadaException;
import br.com.groovymusic.musica.validacao.MusicaSemAlbumException;
import br.com.groovymusic.musica.validacao.MusicaSemNomeException;

public class MusicaService extends BaseService<Musica> {

	public MusicaService(EntityManagerImpl emi) {
		super(emi);
	}
	
	@Override
	public void salvar(Musica t) {
		new Validador()
				.se(t.getNome() == null || t.getNome().isEmpty(), MusicaSemNomeException.class)
				.se(t.getAlbum() == null, MusicaSemAlbumException.class)
				.se(listar(new MusicaFiltro(t.getNome(), t.getAlbum().getId())).size() > 0, MusicaDuplicadaException.class);
		super.salvar(t);
	}
	
	public List<Musica> listar(MusicaFiltro filtro) {
		return queryDinamica("SELECT m FROM Musica m WHERE 1=1", Musica.class)
			.concat(filtro.nome != null, "AND LOWER(m.nome) LIKE LOWER(:nome)", "nome", "%" + filtro.nome + "%")
			.concat(filtro.albumId != null, "AND m.album.id = :albumId", "albumId", filtro.albumId)
			.toQuery()
			.getResultList();
	}
	
}
