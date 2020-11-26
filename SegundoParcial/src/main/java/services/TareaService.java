package services;

import dto.Response;
import model.dto.TareaDTO;
import model.provider.TareaProvider;

import javax.ejb.Stateless;
import javax.ws.rs.*;
import java.util.ArrayList;

@Stateless
@Path("tareas")
public class TareaService {

    //Obtiene las tareas la base de datos para luego pintarlas en la aplicacion
    @GET
    @Consumes("application/json")
    @Produces("application/json")
    @Path("all")
    public ArrayList<TareaDTO> getAllTareas(){
        TareaProvider provider = new TareaProvider();
        ArrayList<TareaDTO> tareaDTOS = provider.getAlltareas();
        return tareaDTOS;
    }


    @GET
    @Consumes("application/json")
    @Produces("application/json")
    @Path("{id}")
    public TareaDTO getTareas(@PathParam("id") String id){

        TareaProvider provider = new TareaProvider();
        TareaDTO dto = provider.getTareaById(id);
        return dto;
    }

    @POST
    @Consumes("application/json")
    @Path("create")
    public dto.Response createTarea(TareaDTO tareaDTO){

        TareaProvider tareaProvider = new TareaProvider();
        tareaProvider.insertTareas(tareaProvider.mapFromDTO(tareaDTO));
        return new Response("Tarea Agregada con exito");
    }

    @DELETE
    @Produces("application/json")
    @Path("delete/{id}")
    public Response deleteTareaById(@PathParam("id") String id){
        TareaProvider provider = new TareaProvider();
        boolean success = provider.deleteTareas(id);
        if(success) return new Response("Tarea eliminada con exito");
        else{
            return  new Response("Ha ocurrido un error");
        }
    }

    @PUT
    @Produces("application/json")
    @Consumes("application/json")
    @Path("edit")
    public Response editTarea(TareaDTO tareaDTO){
        TareaProvider provider = new TareaProvider();
        boolean success = provider.editTarea(tareaDTO);
        if(success) return new Response("Cambio realizado con exito");
        else{
            return new Response("Hubo un error");
        }
    }

}
