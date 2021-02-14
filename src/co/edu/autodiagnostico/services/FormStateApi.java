package co.edu.autodiagnostico.services;

import javax.ws.rs.core.Response;

import co.edu.autodiagnostico.model.FormState;

public interface FormStateApi {
	
	public Response getFormState();
	
	public Response getFormState(int idUsuario);
	
	public Response setFormState(FormState formst);

}
