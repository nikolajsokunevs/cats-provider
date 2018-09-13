package lv.sh.resources;

import lv.sh.dto.Cat;
import lv.sh.service.cat.CatServiceImpl;
import lv.sh.service.cat.ICatService;

import javax.annotation.security.RolesAllowed;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;
import java.util.stream.Collectors;

@Path("cats")
public class CatResource {

    @GET
    @Path("all")
    @Produces("application/json")
    public Response getAll(@QueryParam("firstName") String firstName, @QueryParam("lastName") String lastName) {
        ICatService catService = new CatServiceImpl();
        List<Cat> allCats=catService.getAllCats();
        if (firstName!=null) {
            allCats = allCats.stream().filter(x->firstName.equals(x.getName())).collect(Collectors.toList());
        }
        if (lastName!=null){
            allCats = allCats.stream().filter(x->lastName.equals(x.getLastname())).collect(Collectors.toList());
        }
        return Response.status(200).
                entity(allCats).build();
    }

    @POST
    @Path("add")
    @Produces("application/json")
    public Response post(Cat cat) {
        ICatService catService = new CatServiceImpl();
        catService.addCat(cat);
        return Response.ok().entity(cat).build();
    }

    @GET
    @Path("{id}")
    @Produces("application/json")
    public Response getById(@PathParam("id") String catId) {
        ICatService catService = new CatServiceImpl();
        Cat cat =catService.getCatById(catId);
        if (cat.getId()==null){
            return Response.ok().entity("Cat not exists").build();
        }
        return Response.ok().entity(catService.getCatById(catId)).build();
    }

    @RolesAllowed("ADMIN")
    @DELETE
    @Path("delete/{id}")
    @Produces(MediaType.TEXT_PLAIN)
    public Response delete(@PathParam("id") String catId, @QueryParam("useId") boolean useId) {
        ICatService catService = new CatServiceImpl();
        catService.deleteCat(catId, useId);
        return Response.ok().entity("Cat was deleted").build();
    }


    @PUT
    @Path("update/{id}")
    @Produces("application/json")
    public Response update(@PathParam("id") String catId, Cat cat) {
        ICatService catService = new CatServiceImpl();
        catService.updateCat(catId, cat);
        return Response.ok().entity(cat).build();
    }
}
