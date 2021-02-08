package co.edu.autodiagnostico.model;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "document")
public class Document {
	
	public 	Document() {
		
	}
	
	private int idDocument;
	private String nameDocument;
	
	public int getIdDocument() {
		return idDocument;
	}
	
	public void setIdDocument(int idDocument) {
		this.idDocument = idDocument;
	}
	
	public String getNameDocument() {
		return nameDocument;
	}
	
	public void setNameDocument(String nameDocument) {
		this.nameDocument = nameDocument;
	}
	
	@Override
	public String toString() {
		return "Document [idDocument=" + idDocument + ", nameDocument=" + nameDocument + "]";
	}
	
}
