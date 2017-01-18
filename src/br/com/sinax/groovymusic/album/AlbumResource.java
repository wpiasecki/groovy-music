package br.com.sinax.groovymusic.album;

import java.util.List;
import java.util.stream.Collectors;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import br.com.sinax.groovymusic.config.DI;
import br.com.sinax.groovymusic.config.EntityManagerImpl;

@Path("/album")
public class AlbumResource {

	private EntityManagerImpl em = DI.injector().getInstance(EntityManagerImpl.class);
	AlbumService service = new AlbumService( em  );
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<AlbumView> listar() {
		return service
				.listar()
				.stream()
				.map(AlbumView::new)
				.collect(Collectors.toList());
	}
	
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	public void salvar() {
		
	}

}
