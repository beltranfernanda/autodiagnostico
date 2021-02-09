package co.edu.autodiagnostico.services;
import java.util.Map;
import org.json.JSONArray;
import org.json.JSONObject;

public class UtilImpl implements UtilApi{
	


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

}
