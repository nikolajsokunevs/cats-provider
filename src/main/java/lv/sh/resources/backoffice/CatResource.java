package lv.sh.resources.backoffice;

import lv.sh.dto.Cat;
import lv.sh.service.cat.CatServiceImpl;
import lv.sh.service.cat.ICatService;
import javax.ws.rs.*;
import java.util.List;

@Path("device")
public class CatResource {

    @GET
    @Path("all")
    @Produces("application/json")
    public List<Cat> getAll(){
        ICatService catService=new CatServiceImpl();
        return catService.getAllCats();
    }

    @POST
    @Path("add")
    @Produces("application/json")
    public String post(Cat cat){
        ICatService catService=new CatServiceImpl();
        catService.addCat(cat);
        return "DONE";
    }
}
