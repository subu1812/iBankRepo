package i.bankapp.exception;

import java.time.LocalDate;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ExceptionHandlerController {
	
	@ExceptionHandler(value = ResourceNotFoundException.class)
	public ResponseEntity<ErrorResponse> exception(ResourceNotFoundException exception){
		ErrorResponse errorResponse = new ErrorResponse();
		errorResponse.setErrorCode("Error NOT_FOUND");
		errorResponse.setErrorMessage(exception.getMessage());
		errorResponse.setTimeStamp(LocalDate.now());
		return new ResponseEntity<ErrorResponse>(errorResponse,HttpStatus.NOT_FOUND);
	}
	
}
