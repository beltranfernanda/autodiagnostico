package co.edu.autodiagnostico.services;
import javax.ws.rs.core.Response;

interface CertificateApi {
	
	public Response generateCertificate(String documentNumber, int field);
}
