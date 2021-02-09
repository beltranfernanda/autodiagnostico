package co.edu.autodiagnostico.services;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.Map;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import org.json.JSONArray;


import co.edu.autodiagnostico.model.Document;
import co.edu.autodiagnostico.persistence.ConnectionDB;

@Path("/documentservice")
@Produces({MediaType.APPLICATION_JSON })

public class DocumentImpl implements DocumentApi {
	UtilApi utilObject =  new UtilImpl();
	Connection documentsConn = ConnectionDB.getConn();
	Statement sentencia;
	
	@GET
	@Path("/getdocuments")
	public Response getDocuments() {
		JSONArray jsonResponse = new JSONArray();
		Map<Integer, String> documents = new HashMap<Integer, String>();
		Document documentObject = new Document();
		try {
			sentencia = documentsConn.createStatement();
			String sql = "SELECT idtipodocumento,nombre FROM tipodocumento";
			ResultSet rs= sentencia.executeQuery(sql);
			while(rs.next()) {
				documentObject.setIdDocument(rs.getInt("idtipodocumento"));
				documentObject.setNameDocument(rs.getString("nombre"));
				documents.put(documentObject.getIdDocument(),documentObject.getNameDocument());
			}
		} catch (SQLException e) {
			System.out.println("Error consulting data base: "+e.getCause());
			jsonResponse.put(utilObject.createResponse(500));
			return Response.ok(jsonResponse.toString()).build();
		}
		
		jsonResponse.put(utilObject.createResponse(200));
		jsonResponse.put(utilObject.convertMap(documents,"id","value"));		
		return  Response.ok(jsonResponse.toString()).build();
	}

}
