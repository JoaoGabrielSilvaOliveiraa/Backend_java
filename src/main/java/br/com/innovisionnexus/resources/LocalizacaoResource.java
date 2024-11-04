package br.com.innovisionnexus.resources;

import br.com.innovisionnexus.beans.Localizacao;
import br.com.innovisionnexus.bo.LocalizacaoBO;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("/localizacoes")
public class LocalizacaoResource {
    private final LocalizacaoBO localizacaoBO = new LocalizacaoBO();

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response inserirLocalizacao(Localizacao localizacao) {
        try {
            localizacaoBO.inserir(localizacao);
            return Response.status(Response.Status.CREATED).entity(localizacao).build();
        } catch (Exception e) {
            return Response.status(Response.Status.BAD_REQUEST).entity(e.getMessage()).build();
        }
    }

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response buscarPorId(@PathParam("id") int id) {
        try {
            Localizacao localizacao = localizacaoBO.buscarPorID(id);
            if (localizacao == null) {
                return Response.status(Response.Status.NOT_FOUND).entity("Localização não encontrada.").build();
            }
            return Response.ok(localizacao).build();
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(e.getMessage()).build();
        }
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response listarTodas() {
        try {
            List<Localizacao> localizacoes = localizacaoBO.listarTodas();
            return Response.ok(localizacoes).build();
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(e.getMessage()).build();
        }
    }

    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response atualizarLocalizacao(Localizacao localizacao) {
        try {
            localizacaoBO.atualizar(localizacao);
            return Response.ok(localizacao).build();
        } catch (Exception e) {
            return Response.status(Response.Status.BAD_REQUEST).entity(e.getMessage()).build();
        }
    }

    @DELETE
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response deletarLocalizacao(@PathParam("id") int id) {
        try {
            localizacaoBO.excluir(id);
            return Response.status(Response.Status.NO_CONTENT).build();
        } catch (Exception e) {
            return Response.status(Response.Status.NOT_FOUND).entity(e.getMessage()).build();
        }
    }
}
