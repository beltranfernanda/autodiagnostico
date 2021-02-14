package co.edu.autodiagnostico.model;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "formstate")
public class FormState {
	private int idFormState;
	private String State;
	private User user;
	private Field field;
	
	public int getIdFormState() {
		return idFormState;
	}
	public void setIdFormState(int idFormState) {
		this.idFormState = idFormState;
	}
	public String getState() {
		return State;
	}
	public void setState(String state) {
		State = state;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public Field getField() {
		return field;
	}
	public void setField(Field field) {
		this.field = field;
	}
	
	@Override
	public String toString() {
		return "FormState [idFormState=" + idFormState + ", State=" + State + ", user=" + user + ", field=" + field
				+ "]";
	}
}
