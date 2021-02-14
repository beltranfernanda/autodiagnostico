package co.edu.autodiagnostico.services;

import javax.ws.rs.core.Response;
import co.edu.autodiagnostico.model.Answers;

public interface AnswersApi {
	public Response addExam(Answers [] answers);

}
