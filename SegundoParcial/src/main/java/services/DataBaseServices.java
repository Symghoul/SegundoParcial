package services;

import db.MySQLConection;
import dto.Response;

import javax.ejb.Stateless;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

@Stateless
@Path("db")
public class DataBaseServices {

    @POST
    @Path("create")
    @Produces("application/json")
    public Response createDB(){

        MySQLConection conection = new MySQLConection();
        if(conection.createDataBase())
            return new Response("Base de datos creada con éxito");

        else{
            return new Response("Hubo un error al construir la base de datos");
        }

    }
}
