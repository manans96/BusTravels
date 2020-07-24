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
	
	@ExceptionHandler(BusAppException.BadRequestException.class)
	@ResponseBody
	@ResponseStatus(HttpStatus.FORBIDDEN)
	public Response handleValidationException(BusAppException.BadRequestException bre) {
		
		Response response = Response.badRequest();
		response.addErrorMsgToResponse(bre, bre.getEntity(), "");
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
	
	@ExceptionHandler(BusAppException.InternalServerException.class)
	@ResponseBody
	@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
	public Response handleInternalServerException(BusAppException.InternalServerException ise) {
		
		Response response = Response.internalServerError();
		response.addErrorMsgToResponse(ise, ise.getEntity(), "");
		return response;
	}
	
	@ExceptionHandler(BusAppException.ForbiddenException.class)
	@ResponseBody
	@ResponseStatus(HttpStatus.FORBIDDEN)
	public Response handleForbiddenException(BusAppException.ForbiddenException fe) {
		
		Response response = Response.forbidden();
		response.addErrorMsgToResponse(fe, fe.getEntity(), "");
		return response;
	}
}
