package co.edu.autodiagnostico.model;

public class Answers {
	private int idAnswer;
	private User user;
	private Questions question;
	private String asnwerDesc;
	
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
	public String getAsnwerDesc() {
		return asnwerDesc;
	}
	public void setAsnwerDesc(String asnwerDesc) {
		this.asnwerDesc = asnwerDesc;
	}
	
	

}
