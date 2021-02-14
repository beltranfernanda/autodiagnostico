package co.edu.autodiagnostico.services;
import javax.ws.rs.core.Response;
import co.edu.autodiagnostico.model.Questions;

public interface QuestionApi {
	
	public Response getQuestions();
	
	public Response addQuestion(Questions p);
	
	public Response getQuestions(int id);
	

}
