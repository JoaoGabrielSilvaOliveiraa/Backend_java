package br.com.innovisionnexus.resources;

import br.com.innovisionnexus.beans.InteligenciaArtificial;
import br.com.innovisionnexus.bo.InteligenciaArtificialBO;
import br.com.innovisionnexus.conexoes.ConexaoFactory;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.sql.Connection;
import java.util.List;

@Path("/inteligencia-artificial")
public class InteligenciaArtificialResource {
    private final InteligenciaArtificialBO inteligenciaArtificialBO = new InteligenciaArtificialBO();

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response inserirInteligenciaArtificial(InteligenciaArtificial inteligenciaArtificial) {
        try (Connection connection = new ConexaoFactory().conexao()) { // Abre a conexão
            inteligenciaArtificialBO.inserir(inteligenciaArtificial, connection);
            return Response.status(Response.Status.CREATED).entity(inteligenciaArtificial).build();
        } catch (Exception e) {
            return Response.status(Response.Status.BAD_REQUEST).entity(e.getMessage()).build();
        }
    }

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response buscarPorId(@PathParam("id") int id) {
        try (Connection connection = new ConexaoFactory().conexao()) { // Abre a conexão
            InteligenciaArtificial inteligenciaArtificial = inteligenciaArtificialBO.buscarPorId(id, connection);
            if (inteligenciaArtificial != null) {
                return Response.ok(inteligenciaArtificial).build();
            } else {
                return Response.status(Response.Status.NOT_FOUND).entity("Inteligência Artificial não encontrada").build();
            }
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(e.getMessage()).build();
        }
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response listarTodas() {
        try (Connection connection = new ConexaoFactory().conexao()) { // Abre a conexão
            List<InteligenciaArtificial> inteligencias = inteligenciaArtificialBO.listarTodas(connection);
            return Response.ok(inteligencias).build();
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(e.getMessage()).build();
        }
    }

    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response atualizarInteligenciaArtificial(InteligenciaArtificial inteligenciaArtificial) {
        try (Connection connection = new ConexaoFactory().conexao()) { // Abre a conexão
            inteligenciaArtificialBO.atualizar(inteligenciaArtificial, connection);
            return Response.ok(inteligenciaArtificial).build();
        } catch (Exception e) {
            return Response.status(Response.Status.BAD_REQUEST).entity(e.getMessage()).build();
        }
    }

    @DELETE
    @Path("/{id}")
    public Response deletarInteligenciaArtificial(@PathParam("id") int id) {
        try (Connection connection = new ConexaoFactory().conexao()) { // Abre a conexão
            inteligenciaArtificialBO.deletar(id, connection);
            return Response.status(Response.Status.NO_CONTENT).build();
        } catch (Exception e) {
            return Response.status(Response.Status.NOT_FOUND).entity(e.getMessage()).build();
        }
    }
}
