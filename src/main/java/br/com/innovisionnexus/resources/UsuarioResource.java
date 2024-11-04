package br.com.innovisionnexus.resources;

import br.com.innovisionnexus.beans.Usuario;
import br.com.innovisionnexus.bo.UsuarioBO;
import br.com.innovisionnexus.conexoes.ConexaoFactory;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.sql.Connection;

import java.util.List;

@Path("/usuarios")
public class UsuarioResource {
    private final UsuarioBO usuarioBO = new UsuarioBO();

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response inserirUsuario(Usuario usuario) {
        try (Connection connection = new ConexaoFactory().conexao()) { // Abre a conexão
            usuarioBO.inserir(usuario, connection);
            return Response.status(Response.Status.CREATED).entity(usuario).build();
        } catch (Exception e) {
            return Response.status(Response.Status.BAD_REQUEST).entity(e.getMessage()).build();
        }
    }

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response buscarPorId(@PathParam("id") int id) {
        try (Connection connection = new ConexaoFactory().conexao()) { // Abre a conexão
            Usuario usuario = usuarioBO.buscarPorID(id, connection);
            if (usuario != null) {
                return Response.ok(usuario).build();
            } else {
                return Response.status(Response.Status.NOT_FOUND).entity("Usuário não encontrado").build();
            }
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(e.getMessage()).build();
        }
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response listarTodos() {
        try (Connection connection = new ConexaoFactory().conexao()) { // Abre a conexão
            List<Usuario> usuarios = usuarioBO.buscarTodos(connection);
            return Response.ok(usuarios).build();
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(e.getMessage()).build();
        }
    }

    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response atualizarUsuario(Usuario usuario) {
        try (Connection connection = new ConexaoFactory().conexao()) { // Abre a conexão
            usuarioBO.atualizar(usuario, connection);
            return Response.ok(usuario).build();
        } catch (Exception e) {
            return Response.status(Response.Status.BAD_REQUEST).entity(e.getMessage()).build();
        }
    }

    @DELETE
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response deletarUsuario(@PathParam("id") int id) {
        try (Connection connection = new ConexaoFactory().conexao()) { // Abre a conexão
            usuarioBO.deletar(id, connection);
            return Response.status(Response.Status.NO_CONTENT).build();
        } catch (Exception e) {
            return Response.status(Response.Status.NOT_FOUND).entity(e.getMessage()).build();
        }
    }
}
