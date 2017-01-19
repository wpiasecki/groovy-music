package br.com.groovymusic.musica;

import java.util.List;
import java.util.stream.Collectors;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import br.com.groovymusic.config.DI;
import br.com.groovymusic.config.EntityManagerImpl;
import br.com.groovymusic.config.ValidacaoException;

@Path("/musica")
public class MusicaResource {

	MusicaService service = new MusicaService( DI.injector().getInstance(EntityManagerImpl.class) );
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<MusicaView> listar(
			@QueryParam("nome") String nome, 
			@QueryParam("albumId") Integer albumId) {
		return service
				.listar(new MusicaFiltro(nome, albumId))
				.stream()
				.map(MusicaView::new)
				.collect(Collectors.toList());
	}
	
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	public Response salvar(MusicaView view) {
		return validar(() -> service.salvar(view.converterParaEntidade()));
	}
	
	
	private Response validar(Runnable r) {
		try {
			r.run();
			return Response.ok().build();
		}
		catch (ValidacaoException ve) {
			ve.printStackTrace();
			return Response.status(ve.getStatus()).entity(ve.getMessage()).build();
		}
		catch (Exception e) {
			e.printStackTrace();
			return Response.status(500).build();
		}
	}

	@DELETE
	@Path("/{id}")
	public Response excluir(@PathParam("id") Integer id) {
		service.excluir(service.obterPorId(Musica.class, id));
		return Response.status(204).entity("Excluído com sucesso").build();
	}
	
	@PUT
	@Path("/{id}")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response atualizarOuCriar(@PathParam("id") Integer id, MusicaView view) {
		return validar(() -> service.atualizar(view.atualizarEntidade(service.obterPorId(Musica.class, id))));
	}
}
