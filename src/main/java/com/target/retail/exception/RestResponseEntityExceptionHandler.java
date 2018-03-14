package com.target.retail.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.target.retail.json.entity.ErrorResponse;

@ControllerAdvice
public class RestResponseEntityExceptionHandler {
	
    @ExceptionHandler(BadRequestException.class)
    protected ResponseEntity<ErrorResponse> handleBadRequestException(BadRequestException badRequestException){
    	ErrorResponse error = new ErrorResponse();
		error.setErrorCode(badRequestException.getErrorCode());
		error.setErrorMsg(badRequestException.getErrorMsg());
	     return ResponseEntity
	              .status(HttpStatus.BAD_REQUEST)
	              .body(error);          
    }
    
    @ExceptionHandler(InternalServerException.class)
    protected ResponseEntity<ErrorResponse> handleInternalServerException(InternalServerException internalServerException){
    	ErrorResponse error = new ErrorResponse();
		error.setErrorCode(internalServerException.getErrorCode());
		error.setErrorMsg(internalServerException.getErrorMsg());
	     return ResponseEntity
	              .status(HttpStatus.INTERNAL_SERVER_ERROR)
	              .body(error);          
    }

    @ExceptionHandler(DataNotFoundException.class)
    protected ResponseEntity<ErrorResponse> handleDataNotFounException(DataNotFoundException dataNotFoundException){
    	ErrorResponse error = new ErrorResponse();
		error.setErrorCode(dataNotFoundException.getErrorCode());
		error.setErrorMsg(dataNotFoundException.getErrorMsg());
	     return ResponseEntity
	              .status(HttpStatus.NOT_FOUND)
	              .body(error);          
    }
    
}