package com.kidzoo.globalexception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import com.kidzoo.bean.KidZooExceptionResponse;
import com.kidzoo.constant.ErrorCode;
import com.kidzoo.customexception.KidZooException;

@RestControllerAdvice
public class ToyGlobalExceptionHandler {

	private static final Logger LOGGER = LoggerFactory.getLogger(ToyGlobalExceptionHandler.class);

	@ExceptionHandler(KidZooException.class)
	public ResponseEntity<KidZooExceptionResponse> handleKidZooException(KidZooException exception) {
		LOGGER.error("Error while processing the request {} ", exception.getMessage());
		KidZooExceptionResponse exceptionResposne = new KidZooExceptionResponse();
		exceptionResposne.setErrorCode(exception.getErrorCode());
		exceptionResposne.setErrorMessage(exception.getMessage());
		exceptionResposne.setErrorDetails(exception.getErrorDetails());
		return new ResponseEntity<>(exceptionResposne, HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(Exception.class)
	public ResponseEntity<KidZooExceptionResponse> handleException(Exception exception) {
		LOGGER.error("Error while processing the request {} ", exception.getMessage());
		KidZooExceptionResponse exceptionResposne = new KidZooExceptionResponse();
		exceptionResposne.setErrorCode(ErrorCode.GENERIC_EXCEPTION.getErrorCode());
		exceptionResposne.setErrorMessage(exception.getMessage());
		exceptionResposne.setErrorDetails("Internal Server Error");
		return new ResponseEntity<>(exceptionResposne, HttpStatus.INTERNAL_SERVER_ERROR);
	}

}
