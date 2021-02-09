package co.edu.autodiagnostico.model;

public class Certificate {
	private int idCertificate;
	private User user;
	private Field field;
	private String certificateUrl;
	
	public int getIdCertificate() {
		return idCertificate;
	}
	public void setIdCertificate(int idCertificate) {
		this.idCertificate = idCertificate;
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
	public String getCertificateUrl() {
		return certificateUrl;
	}
	public void setCertificateUrl(String certificateUrl) {
		this.certificateUrl = certificateUrl;
	}
	@Override
	public String toString() {
		return "Certificate [idCertificate=" + idCertificate + ", user=" + user + ", field=" + field
				+ ", certificateUrl=" + certificateUrl + "]";
	}
	
	

}
