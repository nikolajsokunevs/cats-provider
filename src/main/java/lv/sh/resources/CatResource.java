package lv.sh.resources;

import lv.sh.dto.Cat;
import lv.sh.dto.CatGetAll;
import lv.sh.dto.Cat_;
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
    public CatGetAll getAll(){
        ICatService catService=new CatServiceImpl();
        List<Cat_> allCats =catService.getAllCats();
        CatGetAll response = new CatGetAll(allCats);
        return response;
    }

    @POST
    @Path("add")
    @Produces("application/json")
    public Response post(Cat cat){
        ICatService catService=new CatServiceImpl();
        catService.addCat(cat);
        return Response.ok().entity(cat).build();
    }

    @GET
    @Path("{id}")
    @Produces("application/json")
    public Cat getById(@PathParam( "id" ) String catId){
        ICatService catService=new CatServiceImpl();
        List<Cat_> allCats =catService.getAllCats();
        return null;
    }
}
