package i.bankapp.exception;

import java.time.LocalDate;

public class ErrorResponse {
	
	String errorCode;
	String errorMessage;
	LocalDate timeStamp;
	
	public ErrorResponse() {
		super();
	}
	public ErrorResponse(String errorCode, String errorMessage, LocalDate timeStamp) {
		super();
		this.errorCode = errorCode;
		this.errorMessage = errorMessage;
		this.timeStamp = timeStamp;
	}
	public String getErrorCode() {
		return errorCode;
	}
	public void setErrorCode(String errorCode) {
		this.errorCode = errorCode;
	}
	public String getErrorMessage() {
		return errorMessage;
	}
	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}
	public LocalDate getTimeStamp() {
		return timeStamp;
	}
	public void setTimeStamp(LocalDate timeStamp) {
		this.timeStamp = timeStamp;
	}
	
}
