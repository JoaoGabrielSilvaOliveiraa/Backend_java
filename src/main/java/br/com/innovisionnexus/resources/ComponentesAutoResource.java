package br.com.innovisionnexus.resources;

import br.com.innovisionnexus.beans.ComponentesAuto;
import br.com.innovisionnexus.bo.ComponentesAutoBO;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("/componentes")
public class ComponentesAutoResource {
    private final ComponentesAutoBO componentesAutoBO = new ComponentesAutoBO();

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response inserirComponente(ComponentesAuto componente) {
        try {
            componentesAutoBO.inserirComponente(componente);
            return Response.status(Response.Status.CREATED).entity(componente).build();
        } catch (Exception e) {
            return Response.status(Response.Status.BAD_REQUEST).entity(e.getMessage()).build();
        }
    }

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response buscarPorId(@PathParam("id") int id) {
        try {
            ComponentesAuto componente = componentesAutoBO.buscarComponentePorId(id);
            return Response.ok(componente).build();
        } catch (Exception e) {
            return Response.status(Response.Status.NOT_FOUND).entity(e.getMessage()).build();
        }
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response listarTodos() {
        List<ComponentesAuto> componentes = componentesAutoBO.listarTodosComponentes();
        return Response.ok(componentes).build();
    }

    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response atualizarComponente(ComponentesAuto componente) {
        try {
            componentesAutoBO.atualizarComponente(componente);
            return Response.ok(componente).build();
        } catch (Exception e) {
            return Response.status(Response.Status.BAD_REQUEST).entity(e.getMessage()).build();
        }
    }

    @DELETE
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response deletarComponente(@PathParam("id") int id) {
        try {
            componentesAutoBO.deletarComponente(id);
            return Response.status(Response.Status.NO_CONTENT).build();
        } catch (Exception e) {
            return Response.status(Response.Status.NOT_FOUND).entity(e.getMessage()).build();
        }
    }
}
