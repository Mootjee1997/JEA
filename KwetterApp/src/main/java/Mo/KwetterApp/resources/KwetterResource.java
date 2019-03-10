package Mo.KwetterApp.resources;
import Mo.KwetterApp.domain.Kweet;
import Mo.KwetterApp.domain.User;
import Mo.KwetterApp.services.KwetterService;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Path("/kweets")
public class KwetterResource {
    private KwetterService service = new KwetterService();

    @POST
    @Consumes
    public void changeUsername(int id, String username) {
        service.changeUsername(id, username);
    }

    @GET
    @Path("/{userId}")
    @Produces(MediaType.APPLICATION_XML)
    public User loadUserInformation(@PathParam("userId") int id) {
        return service.loadUserInformation(id);
    }

    @GET
    @Path("/find/{searchterm}")
    @Produces(MediaType.APPLICATION_XML)
    public List<Kweet> findKweet(@PathParam("searchterm") String searchterm) {
        return service.findKweet(searchterm);
    }

    @GET
    @Produces(MediaType.APPLICATION_XML)
    public List<Kweet> loadKweets() {
        return service.loadKweets(1);
    }
}
