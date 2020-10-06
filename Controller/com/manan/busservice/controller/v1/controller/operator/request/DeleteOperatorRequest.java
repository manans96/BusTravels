/**
 * 
 */
package com.manan.busservice.controller.v1.controller.operator.request;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Data;
import lombok.ToString;
import lombok.experimental.Accessors;

/**
 * @author Manan Sanghvi
 *
 */
@Data
@Accessors(chain = true)
@ToString
@JsonIgnoreProperties(ignoreUnknown = true)
public class DeleteOperatorRequest {

	private String operatorCode;
}
