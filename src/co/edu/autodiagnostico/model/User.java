package co.edu.autodiagnostico.model;

public class User {
	
	private int idUser;
	private String name;
	Document document;
	private String documentNumber;
	private UserType userType;
	private ContactInformation contactInfo;
	
	public int getIdUser() {
		return idUser;
	}
	public void setIdUser(int idUser) {
		this.idUser = idUser;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Document getDocument() {
		return document;
	}
	public void setDocument(Document document) {
		this.document = document;
	}
	public String getDocumentNumber() {
		return documentNumber;
	}
	public void setDocumentNumber(String documentNumber) {
		this.documentNumber = documentNumber;
	}
	public UserType getUserType() {
		return userType;
	}
	public void setUserType(UserType userType) {
		this.userType = userType;
	}
	public ContactInformation getContactInfo() {
		return contactInfo;
	}
	public void setContactInfo(ContactInformation contactInfo) {
		this.contactInfo = contactInfo;
	}
	
	@Override
	public String toString() {
		return "User [idUser=" + idUser + ", name=" + name + ", document=" + document + ", documentNumber="
				+ documentNumber + ", userType=" + userType + ", contactInfo=" + contactInfo + "]";
	}
	
	
}
