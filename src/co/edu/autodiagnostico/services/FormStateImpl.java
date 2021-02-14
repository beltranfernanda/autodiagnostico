package co.edu.autodiagnostico.services;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.ws.rs.GET;
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

	@Override
	public Response setFormState(FormState formst) {
		// TODO Auto-generated method stub
		return null;
	}

}
