package co.edu.autodiagnostico.model;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "answersoptions")
public class AnswersOptions {
	private int idOptions;
	private String OptionDescription;
	private Questions idQuestion;
	
	public int getIdOptions() {
		return idOptions;
	}
	public void setIdOptions(int idOptions) {
		this.idOptions = idOptions;
	}
	public String getOptionDescription() {
		return OptionDescription;
	}
	public void setOptionDescription(String optionDescription) {
		OptionDescription = optionDescription;
	}
	public Questions getIdQuestion() {
		return idQuestion;
	}
	public void setIdQuestion(Questions idQuestion) {
		this.idQuestion = idQuestion;
	}
	
	@Override
	public String toString() {
		return "AnswersOptions [idOptions=" + idOptions + ", OptionDescription=" + OptionDescription + ", idQuestion="
				+ idQuestion + "]";
	}
}
