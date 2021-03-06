package co.edu.autodiagnostico.model;

public class ContactInformation {
	
	private int idContactInformation;
	private String email;
	private String phone;
	private String address;
	
	public int getIdContactInformation() {
		return idContactInformation;
	}
	public void setIdContactInformation(int idContactInformation) {
		this.idContactInformation = idContactInformation;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}

	@Override
	public String toString() {
		return "ContactInformation [idContactInformation=" + idContactInformation + ", email=" + email + ", phone="
				+ phone + ", address=" + address + "]";
	}

	
}
