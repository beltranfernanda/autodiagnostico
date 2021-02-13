package co.edu.autodiagnostico.services;

import java.util.Map;
import org.json.JSONArray;
import org.json.JSONObject;

public interface UtilApi {
	public JSONArray convertMap(Map<Integer, String> parameter, String key1, String key2);
	
	public JSONObject createResponse(int response);
	
	public Boolean ifExist(String field, int id, String table);
	
	public Boolean ifExist(String field, String value, String table);

}
