package br.com.groovymusic.album;

import java.util.List;
import java.util.stream.Collectors;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import br.com.groovymusic.config.DI;
import br.com.groovymusic.config.EntityManagerImpl;

@Path("/album")
public class AlbumResource {

	AlbumService service = new AlbumService( DI.injector().getInstance(EntityManagerImpl.class) );
	
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
	public Response salvar(AlbumView view) {
		service.salvar(view.converterParaEntidade());
		return Response.ok().build();
	}

}
