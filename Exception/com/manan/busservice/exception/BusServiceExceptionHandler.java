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
	public Response handleDuplicateException(BusAppException.EntityNotFoundException enfe) {
		
		Response response = Response.notFound();
		response.addErrorMsgToResponse(enfe, enfe.getEntity(), "");
		return response;
	}
}
