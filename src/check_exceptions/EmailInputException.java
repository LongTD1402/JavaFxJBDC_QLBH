package check_exceptions;

public class EmailInputException extends Exception {
	private String mes;

	@Override
	public String getMessage() {
		return mes;
	}

	public void setMes(String mes) {
		this.mes = mes;
	}

	public EmailInputException(String mes) {
		this.mes = "[!!!-Lỗi] :" + mes;
	}

}
