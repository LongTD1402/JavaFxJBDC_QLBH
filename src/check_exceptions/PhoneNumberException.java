package check_exceptions;

public class PhoneNumberException extends Exception {
	private String mes;

	public String getMessage() {
		return mes;
	}

	public void setMes(String mes) {
		this.mes = mes;
	}

	public PhoneNumberException(String mes) {
		this.mes = "[!!!-Lỗi] :" + mes;
	}

}
