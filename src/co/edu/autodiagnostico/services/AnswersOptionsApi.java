package co.edu.autodiagnostico.services;

import javax.ws.rs.core.Response;

import org.json.JSONArray;

import co.edu.autodiagnostico.model.AnswersOptions;

public interface AnswersOptionsApi {
	public Response addAnswer(AnswersOptions answeroption);
	
	public JSONArray getAnswers(int idQuestion);

}
