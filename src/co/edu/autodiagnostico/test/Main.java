package co.edu.autodiagnostico.test;

import co.edu.autodiagnostico.services.UtilApi;
import co.edu.autodiagnostico.services.UtilImpl;

public class Main {


	public static void main(String[] args) {	
	
		UtilApi	utilObject = new UtilImpl();
		Boolean response = utilObject.ifExist("idpreguntas", 1, "preguntas");
//		Boolean response = utilObject.ifExist("idtipodocumento", 1, "tipodocumento");
		System.out.println(response);
	}

}
