package slbedu.library.services;


import slbedu.library.dao.UserDAO;
import slbedu.library.model.User;

import java.net.HttpURLConnection;
import java.util.Collection;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Stateless
@Path("user")
public class UserManager {
    
    private static final Response RESPONSE_OK = Response.ok().build();

    @Inject
    private UserDAO userDAO;
    
    @Inject
    private UserContext context;

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public void registerUser(User newUser) {
        userDAO.addUser(newUser);
        context.setCurrentUser(newUser);
    }
    
    @Path("login")
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response loginUser(User user) {
        boolean isUserValid = userDAO.validateUserCredentials(user.getUserName(), user.getPassword());
        if (!isUserValid) {
            return Response.status(HttpURLConnection.HTTP_UNAUTHORIZED).build();
        }
        context.setCurrentUser(user);
        return RESPONSE_OK;
    }
    
    @Path("authenticated")
    @GET
    @Consumes(MediaType.APPLICATION_JSON)
    public Response isAuthenticated() {
        if (context.getCurrentUser() == null) {
            return Response.status(HttpURLConnection.HTTP_NOT_FOUND).build();
        }
        	return RESPONSE_OK;
    }

    @Path("current")
    @GET
    @Consumes(MediaType.TEXT_PLAIN)
    public String getUserName() {
        if (context.getCurrentUser() == null) {
            return null;
        }
        return context.getCurrentUser().getUserName();
    }

    @Path("currentuser")
    @GET
    @Consumes(MediaType.APPLICATION_JSON)
    public User getUser() {
        if (context.getCurrentUser() == null) {
            return null;
        }
        return context.getCurrentUser();
    }
    
    @Path("supervisor")
    @GET
    @Consumes(MediaType.TEXT_PLAIN)
    public String getIsSupervisor() {
        if (context.getCurrentUser() == null) {
            return "false";
        }
        return context.getCurrentUser().isSupervisor() + "";
    }

	@Path("logout")
	@GET
	@Consumes(MediaType.TEXT_PLAIN)
	public void logoutUser() {
		context.setCurrentUser(null);
	}
	@Path("all")
	@GET
	@Produces("application/json")
	public Collection<User> getAll() {
		return userDAO.findAllUsers();
	}
}
