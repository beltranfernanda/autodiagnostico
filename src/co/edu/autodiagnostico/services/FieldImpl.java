package co.edu.autodiagnostico.services;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import org.json.JSONArray;
import org.json.JSONObject;
import co.edu.autodiagnostico.model.Field;
import co.edu.autodiagnostico.persistence.ConnectionDB;

@Path("/fieldservice")
@Produces({MediaType.APPLICATION_JSON })

public class FieldImpl implements FieldApi{
	UtilApi utilObject = new UtilImpl();
	Connection objectConn = ConnectionDB.getConn();
	Statement sentencia;

	@GET
	@Path("/getfields")
	@Override
	public Response getFields() {
		JSONObject jsonResponse = new JSONObject();
		JSONArray fields = new JSONArray();
		Field fieldob = new Field();
		try {
			sentencia = objectConn.createStatement();
			String sql = "SELECT * FROM especialidad";
			ResultSet rs= sentencia.executeQuery(sql);
			while(rs.next()) {
				JSONObject fieldjson = new JSONObject();
				fieldob.setIdField(rs.getInt("idEspecialidad"));
				fieldob.setNameField(rs.getString("nombreEspecialidad"));
				fieldjson.put("idEspecialidad",fieldob.getIdField());
				fieldjson.put("nombreEspecialidad",fieldob.getNameField());
				fields.put(fieldjson);
			}
			jsonResponse = utilObject.createResponse(200);
			jsonResponse.put("data",fields);
		} catch (SQLException e) {
			System.out.println("Error consulting data base: "+e.getCause());
			jsonResponse = utilObject.createResponse(500);
			return Response.ok(jsonResponse.toString()).build();
		}		
		return  Response.ok(jsonResponse.toString()).build();
	}

}
