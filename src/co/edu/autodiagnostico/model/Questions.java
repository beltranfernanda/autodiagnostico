package co.edu.autodiagnostico.model;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "question")
public class Questions {
	private int idQuestion;
	private String question;
	private Field field;
	
	public int getIdQuestion() {
		return idQuestion;
	}
	public void setIdQuestion(int idQuestion) {
		this.idQuestion = idQuestion;
	}
	public String getQuestion() {
		return question;
	}
	public void setQuestion(String question) {
		this.question = question;
	}
	
	public Field getField() {
		return field;
	}
	public void setField(Field field) {
		this.field = field;
	}
	
	@Override
	public String toString() {
		return "Questions [idQuestion=" + idQuestion + ", question=" + question + "]";
	}
	
	

}
