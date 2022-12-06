package check_exceptions;

public class NullFieldException extends Exception{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String mes;

	public String getMessage() {
		return mes;
	}

	public void setMes(String mes) {
		this.mes = mes;
	}

	public NullFieldException(String mes) {
		this.mes ="[!!!-Lỗi:] :"+ mes;
	}
	
}
