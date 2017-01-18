package br.com.sinax.groovymusic.artista;

import java.util.List;
import java.util.stream.Collectors;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import br.com.sinax.groovymusic.config.DI;
import br.com.sinax.groovymusic.config.EntityManagerImpl;

@Path("/artista")
public class ArtistaResource {

	ArtistaService service = new ArtistaService( DI.injector().getInstance(EntityManagerImpl.class) );
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<ArtistaView> listar() {
		return service
				.listar()
				.stream()
				.map(ArtistaView::new)
				.collect(Collectors.toList());
	}
	
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	public Response salvar(ArtistaView view) {
		service.salvar(view.converterParaEntidade());
		return Response.ok().build();
	}
	
}
