package com.manan.busservice.response;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.manan.busservice.utility.DateUtils;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class Response {
	
	private ResponseError responseError;
	private ResponseStatus status;
	
	private Response() {}
	
	public static Response notFound() {
		
		Response response = new Response()
				.setStatus(ResponseStatus.NOT_FOUND);
		return response;
	}
	
	public static Response duplicate() {
		
		Response response = new Response()
				.setStatus(ResponseStatus.DUPLICATE_ENTITY);
		return response;
	}
	
	public static Response validationError() {
		
		Response response = new Response()
				.setStatus(ResponseStatus.VALIDATION_EXCEPTION);
		return response;
	}
	
	public void addErrorMsgToResponse(Exception ex, ResponseEntity entity, String details) {
		ResponseError error = new ResponseError()
				.setMessage(entity.getResponseEntity())
				.setDetails(details)
				.setTimestamp(DateUtils.today());
		setResponseError(error);
	}
	
	
	@Data
	@Accessors(chain = true)
	@NoArgsConstructor
	@JsonInclude(JsonInclude.Include.NON_NULL)
	@JsonIgnoreProperties(ignoreUnknown = true)
	private class ResponseError {
		
		private String message;
		private String details;
		private Date timestamp;
	}

}
