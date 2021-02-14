package co.edu.autodiagnostico.model;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "answers")
public class Answers {
	private int idAnswer;
	private User user;
	private Questions question;
	private String answerDesc;
	
	public int getIdAnswer() {
		return idAnswer;
	}
	public void setIdAnswer(int idAnswer) {
		this.idAnswer = idAnswer;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public Questions getQuestion() {
		return question;
	}
	public void setQuestion(Questions question) {
		this.question = question;
	}
	public String getAnswerDesc() {
		return answerDesc;
	}
	public void setAnswerDesc(String asnwerDesc) {
		this.answerDesc = asnwerDesc;
	}
	
	

}
