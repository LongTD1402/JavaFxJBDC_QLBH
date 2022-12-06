package check_exceptions;

public class CheckListSP_HangSX extends Exception {
	private String message;

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public CheckListSP_HangSX(String message) {
		this.message = "[!!!-Lỗi] :" + message;
	}

}
