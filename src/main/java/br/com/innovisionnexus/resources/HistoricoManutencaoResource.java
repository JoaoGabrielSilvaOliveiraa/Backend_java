package br.com.innovisionnexus.resources;

import br.com.innovisionnexus.beans.HistoricoManutencao;
import br.com.innovisionnexus.bo.HistoricoManutencaoBO;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("/historicos")
public class HistoricoManutencaoResource {

	private final HistoricoManutencaoBO historicoManutencaoBO;

	public HistoricoManutencaoResource() {
		this.historicoManutencaoBO = new HistoricoManutencaoBO();
	}

	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response inserirHistorico(HistoricoManutencao manutencao) {
		try {
			historicoManutencaoBO.inserirHistorico(manutencao);
			return Response.status(Response.Status.CREATED).entity(manutencao).build();
		} catch (Exception e) {
			return Response.status(Response.Status.BAD_REQUEST).entity(e.getMessage()).build();
		}
	}

	@GET
	@Path("/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response buscarPorId(@PathParam("id") int id) {
		try {
			HistoricoManutencao manutencao = historicoManutencaoBO.buscarHistoricoPorId(id);
			return Response.ok(manutencao).build();
		} catch (Exception e) {
			return Response.status(Response.Status.NOT_FOUND).entity(e.getMessage()).build();
		}
	}

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response listarTodos() {
		List<HistoricoManutencao> manutencoes = historicoManutencaoBO.listarTodosHistoricos();
		return Response.ok(manutencoes).build();
	}

	@PUT
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response atualizarHistorico(HistoricoManutencao manutencao) {
		try {
			historicoManutencaoBO.atualizarHistorico(manutencao);
			return Response.ok(manutencao).build();
		} catch (Exception e) {
			return Response.status(Response.Status.BAD_REQUEST).entity(e.getMessage()).build();
		}
	}

	@DELETE
	@Path("/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response deletarHistorico(@PathParam("id") int id) {
		try {
			historicoManutencaoBO.excluirHistorico(id);
			return Response.status(Response.Status.NO_CONTENT).build();
		} catch (Exception e) {
			return Response.status(Response.Status.NOT_FOUND).entity(e.getMessage()).build();
		}
	}
}
