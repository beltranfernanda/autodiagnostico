package co.edu.autodiagnostico.services;

import java.io.ByteArrayOutputStream;

public interface PDFApi {
	
	public ByteArrayOutputStream getPDF(String name, String especialidad, String numeroDoc);
	
	

}
