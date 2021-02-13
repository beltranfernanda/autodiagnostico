package co.edu.autodiagnostico.services;
import javax.ws.rs.core.Response;

import co.edu.autodiagnostico.model.Questions;
import co.edu.autodiagnostico.model.ResponsePost;

public interface QuestionApi {
	
	public Response getQuestions();
	
	public ResponsePost addQuestion(Questions p);
	

}
