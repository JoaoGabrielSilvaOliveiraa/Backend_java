package br.com.innovisionnexus.resources;

import br.com.innovisionnexus.beans.Diagnostico;
import br.com.innovisionnexus.bo.DiagnosticoBO;
import br.com.innovisionnexus.conexoes.ConexaoFactory;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.sql.Connection;
import java.util.List;

@Path("/diagnosticos")
public class DiagnosticoResource {
    private final DiagnosticoBO diagnosticoBO = new DiagnosticoBO();

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response inserirDiagnostico(Diagnostico diagnostico) {
        try (Connection connection = new ConexaoFactory().conexao()) { // Abre a conexão
            diagnosticoBO.inserir(diagnostico, connection);
            return Response.status(Response.Status.CREATED).entity(diagnostico).build();
        } catch (Exception e) {
            return Response.status(Response.Status.BAD_REQUEST).entity(e.getMessage()).build();
        }
    }

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response buscarPorId(@PathParam("id") int id) {
        try (Connection connection = new ConexaoFactory().conexao()) { // Abre a conexão
            Diagnostico diagnostico = diagnosticoBO.buscarPorID(id, connection);
            if (diagnostico != null) {
                return Response.ok(diagnostico).build();
            } else {
                return Response.status(Response.Status.NOT_FOUND).entity("Diagnóstico não encontrado").build();
            }
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(e.getMessage()).build();
        }
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response listarTodos() {
        try (Connection connection = new ConexaoFactory().conexao()) { // Abre a conexão
            List<Diagnostico> diagnosticos = diagnosticoBO.buscarTodos(connection);
            return Response.ok(diagnosticos).build();
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(e.getMessage()).build();
        }
    }

    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response atualizarDiagnostico(Diagnostico diagnostico) {
        try (Connection connection = new ConexaoFactory().conexao()) { // Abre a conexão
            diagnosticoBO.atualizar(diagnostico, connection);
            return Response.ok(diagnostico).build();
        } catch (Exception e) {
            return Response.status(Response.Status.BAD_REQUEST).entity(e.getMessage()).build();
        }
    }

    @DELETE
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response deletarDiagnostico(@PathParam("id") int id) {
        try (Connection connection = new ConexaoFactory().conexao()) { // Abre a conexão
            diagnosticoBO.deletar(id, connection);
            return Response.status(Response.Status.NO_CONTENT).build();
        } catch (Exception e) {
            return Response.status(Response.Status.NOT_FOUND).entity(e.getMessage()).build();
        }
    }
}
