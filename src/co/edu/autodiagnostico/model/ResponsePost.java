package co.edu.autodiagnostico.model;

public class ResponsePost {
	private boolean status;
	private String message;
	private int code;
	
	public boolean isStatus() {
		return status;
	}
	public void setStatus(boolean status) {
		this.status = status;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public int getCode() {
		return code;
	}
	public void setCode(int code) {
		this.code = code;
	}
	@Override
	public String toString() {
		return "ResponsePost [status=" + status + ", message=" + message + ", code=" + code + "]";
	}	
	
}
