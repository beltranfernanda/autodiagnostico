package co.edu.autodiagnostico.services;

import javax.ws.rs.core.Response;
import co.edu.autodiagnostico.model.User;

public interface UserApi {
	
	public Response addUser(User user);
	
	public Response userExist(String id);
	
	public Response getUser(int iddoc);
	
}
