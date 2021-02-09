package co.edu.autodiagnostico.model;

public class UserType { 
	
	private int idUserType;
	private String userTypeDesc;
	
	public int getIdUserType() {
		return idUserType;
	}
	public void setIdUserType(int idUserType) {
		this.idUserType = idUserType;
	}
	public String getUserTypeDesc() {
		return userTypeDesc;
	}
	public void setUserTypeDesc(String userTypeDesc) {
		this.userTypeDesc = userTypeDesc;
	}
	
	@Override
	public String toString() {
		return "UserType [idUserType=" + idUserType + ", userTypeDesc=" + userTypeDesc + "]";
	}

}
