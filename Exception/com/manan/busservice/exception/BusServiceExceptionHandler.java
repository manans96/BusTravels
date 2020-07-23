/**
 * 
 */
package com.manan.busservice.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.manan.busservice.response.Response;

/**
 * @author Manan Sanghvi
 *
 */
@ControllerAdvice
public class BusServiceExceptionHandler {

	@ExceptionHandler(BusAppException.EntityNotFoundException.class)
	@ResponseBody
	@ResponseStatus(HttpStatus.NOT_FOUND)
	public Response handleNotFoundException(BusAppException.EntityNotFoundException enfe) {
		
		Response response = Response.notFound();
		response.addErrorMsgToResponse(enfe, enfe.getEntity(), "");
		return response;
	}
	
	@ExceptionHandler(BusAppException.DuplicateEntityException.class)
	@ResponseBody
	@ResponseStatus(HttpStatus.CONFLICT)
	public Response handleDuplicateException(BusAppException.DuplicateEntityException dee) {
		
		Response response = Response.notFound();
		response.addErrorMsgToResponse(dee, dee.getEntity(), "");
		return response;
	}
	
	@ExceptionHandler(BusAppException.ValidationException.class)
	@ResponseBody
	@ResponseStatus(HttpStatus.FORBIDDEN)
	public Response handleValidationException(BusAppException.ValidationException ve) {
		
		Response response = Response.validationError();
		response.addErrorMsgToResponse(ve, ve.getEntity(), "");
		return response;
	}
	
	@ExceptionHandler(BusAppException.WrongCredentialsException.class)
	@ResponseBody
	@ResponseStatus(HttpStatus.UNAUTHORIZED)
	public Response handleWrongCredentialsException(BusAppException.WrongCredentialsException wce) {
		
		Response response = Response.credentialsError();
		response.addErrorMsgToResponse(wce, wce.getEntity(), "");
		return response;
	}
}
