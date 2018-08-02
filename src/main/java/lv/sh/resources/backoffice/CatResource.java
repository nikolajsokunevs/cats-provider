package lv.sh.resources.backoffice;

import lv.sh.dto.Cat;
import lv.sh.service.cat.CatServiceImpl;
import lv.sh.service.cat.ICatService;
import javax.ws.rs.*;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("cats")
public class CatResource {

    @GET
    @Path("all")
    @Produces("application/json")
    public List<Cat> getAll(){
        ICatService catService=new CatServiceImpl();
        List<Cat> allCats =catService.getAllCats();
        return allCats;
    }

    @POST
    @Path("add")
    @Produces("application/json")
    public Response post(Cat cat){
        ICatService catService=new CatServiceImpl();
        catService.addCat(cat);
        return Response.ok().entity(cat).build();
    }
}
