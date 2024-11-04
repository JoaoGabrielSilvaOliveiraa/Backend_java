package br.com.innovisionnexus.resources;

import br.com.innovisionnexus.beans.Automovel;
import br.com.innovisionnexus.bo.AutomovelBO;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("/automoveis")
public class AutomovelResource {
    private final AutomovelBO automovelBO = new AutomovelBO();

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response inserirAutomovel(Automovel automovel) {
        try {
            automovelBO.inserirAutomovel(automovel);
            return Response.status(Response.Status.CREATED).entity(automovel).build();
        } catch (Exception e) {
            return Response.status(Response.Status.BAD_REQUEST).entity(e.getMessage()).build();
        }
    }

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response buscarPorId(@PathParam("id") int id) {
        try {
            Automovel automovel = automovelBO.buscarAutomovelPorId(id);
            return Response.ok(automovel).build();
        } catch (Exception e) {
            return Response.status(Response.Status.NOT_FOUND).entity(e.getMessage()).build();
        }
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response listarTodos() {
        List<Automovel> automoveis = automovelBO.listarTodosAutomoveis();
        return Response.ok(automoveis).build();
    }

    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response atualizarAutomovel(Automovel automovel) {
        try {
            automovelBO.atualizarAutomovel(automovel);
            return Response.ok(automovel).build();
        } catch (Exception e) {
            return Response.status(Response.Status.BAD_REQUEST).entity(e.getMessage()).build();
        }
    }

    @DELETE
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response deletarAutomovel(@PathParam("id") int id) {
        try {
            automovelBO.excluirAutomovel(id);
            return Response.status(Response.Status.NO_CONTENT).build();
        } catch (Exception e) {
            return Response.status(Response.Status.NOT_FOUND).entity(e.getMessage()).build();
        }
    }
}
