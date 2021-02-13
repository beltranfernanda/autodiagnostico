package co.edu.autodiagnostico.services;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Map;
import org.json.JSONArray;
import org.json.JSONObject;

import co.edu.autodiagnostico.persistence.ConnectionDB;

public class UtilImpl implements UtilApi{
	Connection objectConn = ConnectionDB.getConn();
	Statement sentencia;


	@Override
	public JSONObject createResponse(int response) {
		JSONObject responseJson = new JSONObject();
		responseJson.put("response", response);
		System.out.println(responseJson.toString());
		return responseJson;
		
	}

	@Override
	public JSONArray convertMap(Map<Integer, String> parameter, String key1, String key2) {
		JSONArray jsonConverter = new JSONArray();
		for (Map.Entry<Integer, String> entry : parameter.entrySet()) {
			JSONObject jsonTemp = new JSONObject();
			jsonTemp.put(key1,entry.getKey());
			jsonTemp.put(key2,entry.getValue() );
			jsonConverter.put(jsonTemp);
		}
		System.out.println(jsonConverter);
		return jsonConverter;
	}
	
	@Override
	public Boolean ifExist(String field, int id, String table) {		
		try {
			String sql = "SELECT COUNT(*) AS exist FROM " + table + 
					" WHERE " + field + " = ?";
			PreparedStatement consultExist = objectConn.prepareStatement(sql);
			consultExist.setInt(1, id);
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
