package co.edu.autodiagnostico.services;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import org.json.JSONArray;
import org.json.JSONObject;
import co.edu.autodiagnostico.model.Document;
import co.edu.autodiagnostico.model.User;
import co.edu.autodiagnostico.model.UserType;
import co.edu.autodiagnostico.persistence.ConnectionDB;


@Path("/users")
@Produces({MediaType.APPLICATION_JSON })

public class UserImpl implements UserApi{
	UtilApi utilObject = new UtilImpl();
	Connection objectConn = ConnectionDB.getConn();
	Statement sentencia;

	
	@POST
	@Path("/adduser")
	@Consumes(MediaType.APPLICATION_JSON)
	@Override
	public Response addUser(User user) {
		JSONObject response = new JSONObject();
		try {
			if(!utilObject.ifExist("numerodoc",user.getDocumentNumber(), "user")){
				String sql = "INSERT INTO user (nombre,idtipodoc,numerodoc,idtipousuario)" + 
					     " VALUES (?,?,?,?)";
				PreparedStatement addStatement = objectConn.prepareStatement(sql);
				addStatement.setString(1 , user.getName());
				addStatement.setInt(2, user.getDocument().getIdDocument());
				addStatement.setString(3, user.getDocumentNumber());
				addStatement.setInt(4, user.getUserType().getIdUserType());
				addStatement.executeUpdate();	
				response = utilObject.createResponse(200);
				response.put("mensaje", "Se agrego el usuario correctamente");
			}else {
				response = utilObject.createResponse(400);
				response.put("mensaje", "El usuario ya existe");
			}
		} catch (SQLException e) {
			response = utilObject.createResponse(500);
			response.put("mensaje", "Ocurrio un error al agregar el usuario");
			System.out.println("Ocurrio un error al consultar la DB: "+e.getCause());
			e.printStackTrace();
		}
		return Response
				.ok(response.toString())
				.header("Access-Control-Allow-Origin","*")
				.build();
	}

	@GET
	@Path("/existuser/{id}")
	@Override
	public Response userExist(@PathParam("id") String id) {
		JSONObject response = new JSONObject();
		Boolean exist = utilObject.ifExist("numerodoc", id,"user");
		if(exist) {
			response = utilObject.createResponse(200);
			response.put("exist", true);
		}else {
			response = utilObject.createResponse(200);
			response.put("exist", false);
		}
		System.out.println(response.toString());
		return Response.ok(response.toString()).build();
	}
	
	
	@GET
	@Path("/getuser/{id}")
	@Override
	public Response getUser(@PathParam("id") int iddoc) {
		JSONObject jsonResponse = new JSONObject();
		JSONArray user = new JSONArray();
		User userOb = new User();
		try {
			String sql = "SELECT * FROM user WHERE numerodoc = ?";
			PreparedStatement statement = objectConn.prepareStatement(sql);
			statement.setInt(1, iddoc);
			ResultSet rs= statement.executeQuery();
			while(rs.next()) {
				JSONObject userjson = new JSONObject();
				userOb.setIdUser(rs.getInt("iduser"));
				userOb.setName(rs.getString("nombre"));
				userOb.setDocument(new Document(rs.getInt("idtipodoc")));
				userOb.setUserType(new UserType(rs.getInt("idtipousuario")));
				userjson.put("iduser", userOb.getIdUser());
				userjson.put("nombre", userOb.getName());
				userjson.put("idtipodoc", userOb.getDocument().getIdDocument());
				userjson.put("idtipousuario", userOb.getUserType().getIdUserType());
				user.put(userjson);
			}
			jsonResponse = utilObject.createResponse(200);
			jsonResponse.put("data",user);
		} catch (SQLException e) {
			System.out.println("Error consulting data base: "+e.getCause());
			jsonResponse = utilObject.createResponse(500);
			return Response.ok(jsonResponse.toString()).build();
		}		
		return  Response.ok(jsonResponse.toString()).build();
	}
	

}
