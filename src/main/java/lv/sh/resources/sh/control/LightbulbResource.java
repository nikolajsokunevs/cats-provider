package lv.sh.resources.sh.control;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

@Path("lightbulb")
public class LightbulbResource {

    @GET
    @Path("{id}/{status}")
    @Produces("application/json")
    public String put(@PathParam("id") String id, @PathParam("status") int status){

        return "DONE";
    }
}
