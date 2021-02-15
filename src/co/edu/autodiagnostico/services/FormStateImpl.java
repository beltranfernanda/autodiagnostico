package co.edu.autodiagnostico.services;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import org.json.JSONArray;
import org.json.JSONObject;
import co.edu.autodiagnostico.model.Field;
import co.edu.autodiagnostico.model.FormState;
import co.edu.autodiagnostico.model.User;
import co.edu.autodiagnostico.persistence.ConnectionDB;

@Path("/formstservice")
@Produces({MediaType.APPLICATION_JSON })

public class FormStateImpl implements FormStateApi{
	UtilApi utilObject = new UtilImpl();
	Connection objectConn = ConnectionDB.getConn();
	Statement sentencia;

	@GET
	@Path("/getformst")
	@Override
	public Response getFormState() {
		JSONObject jsonResponse = new JSONObject();
		JSONArray formstate = new JSONArray();
		FormState fstate = new FormState();
		try {
			sentencia = objectConn.createStatement();
			String sql = "SELECT * FROM estadoformulario";
			ResultSet rs= sentencia.executeQuery(sql);
			while(rs.next()) {
				JSONObject formstatejson = new JSONObject();
				fstate.setIdFormState(rs.getInt("idestadoformulario"));
				fstate.setState(rs.getString("estado"));
				fstate.setUser(new User(rs.getInt("idusuario")));
				fstate.setField(new Field(rs.getInt("idespecialidad")));
				formstatejson.put("idestadoformulario", fstate.getIdFormState() );
				formstatejson.put("estado", fstate.getState());
				formstatejson.put("idusuario", fstate.getUser().getIdUser());
				formstatejson.put("idespecialidad", fstate.getField().getIdField());
				formstate.put(formstatejson);
			}
			jsonResponse = utilObject.createResponse(200);
			jsonResponse.put("data",formstate);
		} catch (SQLException e) {
			System.out.println("Error consulting data base: "+e.getCause());
			jsonResponse = utilObject.createResponse(500);
			return Response.ok(jsonResponse.toString()).build();
		}		
		return  Response.ok(jsonResponse.toString()).build();
	}
	
	@GET
	@Path("/getformst/{id}")
	@Override
	public Response getFormState(@PathParam("id") int idUsuario) {
		JSONObject jsonResponse = new JSONObject();
		JSONArray formstate = new JSONArray();
		FormState fstate = new FormState();
		try {
			String sql = "SELECT * FROM estadoformulario WHERE idusuario = ?";
			PreparedStatement sentencia = objectConn.prepareStatement(sql);
			sentencia.setInt(1, idUsuario);
			ResultSet rs= sentencia.executeQuery();
			while(rs.next()) {
				JSONObject formstatejson = new JSONObject();
				fstate.setIdFormState(rs.getInt("idestadoformulario"));
				fstate.setState(rs.getString("estado"));
				fstate.setUser(new User(rs.getInt("idusuario")));
				fstate.setField(new Field(rs.getInt("idespecialidad")));
				formstatejson.put("idestadoformulario", fstate.getIdFormState() );
				formstatejson.put("estado", fstate.getState());
				formstatejson.put("idusuario", fstate.getUser().getIdUser());
				formstatejson.put("idespecialidad", fstate.getField().getIdField());
				formstate.put(formstatejson);
			}
			jsonResponse = utilObject.createResponse(200);
			jsonResponse.put("data",formstate);
		} catch (SQLException e) {
			System.out.println("Error consulting data base: "+e.getCause());
			jsonResponse = utilObject.createResponse(500);
			return Response.ok(jsonResponse.toString()).build();
		}		
		return  Response.ok(jsonResponse.toString()).build();
	}

	@POST
	@Path("/setstate")
	@Consumes(MediaType.APPLICATION_JSON)
	@Override
	public Response setFormState(FormState formst) {
		JSONObject response = new JSONObject();
		try {
			if(!ifExistState(formst.getUser().getIdUser(),formst.getField().getIdField() )){
				String sql = "INSERT INTO estadoformulario (estado,idusuario,idespecialidad)" + 
						     " VALUES (?,?,?)";
				PreparedStatement addStatement = objectConn.prepareStatement(sql);
				addStatement.setString(1 , formst.getState());
				addStatement.setInt(2, formst.getUser().getIdUser());
				addStatement.setInt(3, formst.getField().getIdField());
				addStatement.executeUpdate();
				response = utilObject.createResponse(200);
				response.put("mensaje", "Se agrego estado del formulario correctamente");
			}else {
				response = utilObject.createResponse(400);
				response.put("mensaje", "El estado del formulario ya existe");
			}
		} catch (SQLException e) {
			response = utilObject.createResponse(500);
			response.put("mensaje", "Ocurrio un error al realizar la consulta a la base de datos");
			System.out.println("Ocurrio un error al consultar la DB: "+e.getCause());
			e.printStackTrace();
		}		
		return Response
				.ok(response.toString())
				.header("Access-Control-Allow-Origin","*")
				.build();
	}
	
	
	@PUT
	@Path("/updatestate/{id}/{state}/{idesp}")
	@Consumes(MediaType.APPLICATION_JSON)
	@Override
	public Response updateFormState(@PathParam("state") String estado, @PathParam("id") int idUsuario ,
			@PathParam("idesp") int idEspecialidad){
		JSONObject response = new JSONObject();
		try {
			if(utilObject.ifExist("idusuario",idUsuario, "estadoformulario")){
				String sql = "UPDATE estadoformulario set estado = ? WHERE idusuario = ? AND idespecialidad = ?";
				PreparedStatement addStatement = objectConn.prepareStatement(sql);
				addStatement.setString(1, estado);
				addStatement.setInt(2, idUsuario);
				addStatement.setInt(3, idEspecialidad);
				addStatement.executeUpdate();
				response = utilObject.createResponse(200);
				response.put("mensaje", "Se actualizó el estado correctamente");
			}else {
				response = utilObject.createResponse(400);
				response.put("mensaje", "El usuario ingresado no existe en la tabla estado del formulario");
			}
		} catch (SQLException e) {
			response = utilObject.createResponse(500);
			response.put("mensaje", "Ocurrio un error al realizar la consulta a la base de datos");
			System.out.println("Ocurrio un error al consultar la DB: "+e.getCause());
			e.printStackTrace();
		}		
		return Response
				.ok(response.toString())
				.header("Access-Control-Allow-Origin","*")
				.build();
	}
	
	public Boolean ifExistState(int idUser, int idField) {		
		try {
			String sql = "SELECT COUNT(*) AS exist FROM estadoformulario WHERE  idusuario = ?"
					+ " AND idespecialidad = ?";
			PreparedStatement consultExist = objectConn.prepareStatement(sql);
			consultExist.setInt(1, idUser);
			consultExist.setInt(2, idField);
			ResultSet result = consultExist.executeQuery();
			while(result.next()) {
				System.out.println(result.getString("exist"));
				if(!result.getString("exist").equals("0")) {
					return true;
				}
			}
		} catch (SQLException e) {
			System.out.println("Ocurrio un error al consultar la BD. Error: "+e.getStackTrace());
			e.getMessage();
			e.printStackTrace();
		}
		return false;
	}
}
