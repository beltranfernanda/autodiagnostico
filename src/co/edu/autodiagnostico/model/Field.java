package co.edu.autodiagnostico.model;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "field")
public class Field {
	private int idField;
	private String nameField;
	
	public Field() {
		
	}
	
	public Field(int idField) {
		this.idField = idField;
	}
	
	public int getIdField() {
		return idField;
	}
	public void setIdField(int idField) {
		this.idField = idField;
	}
	public String getNameField() {
		return nameField;
	}
	public void setNameField(String nameField) {
		this.nameField = nameField;
	}
	
	@Override
	public String toString() {
		return "Field [idField=" + idField + ", nameField=" + nameField + "]";
	}
	
	
	

}
