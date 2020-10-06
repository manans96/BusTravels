/**
 * 
 */
package com.manan.busservice.controller.v1.controller.operator;

import java.security.Principal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.manan.busservice.controller.v1.controller.operator.request.AddOperatorRequest;
import com.manan.busservice.controller.v1.controller.operator.request.DeleteOperatorRequest;
import com.manan.busservice.controller.v1.controller.operator.request.GetOperatorRequest;
import com.manan.busservice.controller.v1.controller.operator.request.UpdateOperatorDetailsRequest;
import com.manan.busservice.dto.model.operator.BusOperator;
import com.manan.busservice.dto.model.user.User;
import com.manan.busservice.service.Services;

/**
 * @author Manan Sanghvi
 *
 */
@RestController
@RequestMapping("/api/v1/operator")
public class OperatorController {
	
	private Services.Container services;
	
	@Autowired
	public OperatorController(Services.Container services) {
		this.services = services;
	}

	@PostMapping("/add")
	@PreAuthorize("hasRole('user')")
	public ResponseEntity<String> addNewOperator(Principal principal, @RequestBody AddOperatorRequest operator) {
		
		BusOperator busOperator = new BusOperator()
				.setOperator(new User().setUserName(principal.getName()))
				.setOperatorCode(operator.getOperatorCode())
				.setOperatorName(operator.getOperatorName())
				.setOperatorDetails(operator.getOperatorDetails());
		services.busOperatorService.addNewOperator(busOperator);
		
		return new ResponseEntity<>("Bus Operator " + operator.getOperatorName() + " created with operator " + principal.getName(), HttpStatus.CREATED);
	}
	
	@PatchMapping("/updatedetails")
	@PreAuthorize("hasRole('operator')")
	public ResponseEntity<String> updateOperatorDetails(Principal principal, @RequestBody UpdateOperatorDetailsRequest operatorDetails){
		
		BusOperator busOperator = new BusOperator()
				.setOperatorCode(operatorDetails.getOperatorCode())
				.setOperatorDetails(operatorDetails.getOperatorDetails());
		services.busOperatorService.updateBusOperatorDetails(busOperator);
		
		return new ResponseEntity<>("Details for the operator updated", HttpStatus.OK);
	}
	
	@DeleteMapping("/delete")
	@PreAuthorize("hasRole('operator') or hasRole('admin')")
	public ResponseEntity<String> deleteOperator(Principal principal, @RequestBody DeleteOperatorRequest deleteOperator){
		
		services.busOperatorService.deleteBusOperator(deleteOperator.getOperatorCode());
		
		return new ResponseEntity<>("Operator " + deleteOperator.getOperatorCode() + " deleted", HttpStatus.OK);
	}
	
	@GetMapping("/view")
	@PreAuthorize("hasRole('operator')")
	public ResponseEntity<BusOperator> getDetails(Principal principal, @RequestBody GetOperatorRequest operator){
		
		return new ResponseEntity<>(services.busOperatorService.viewBusOperator(operator.getOperatorCode()), HttpStatus.OK);
	}
	
	@GetMapping("/viewall")
	@PreAuthorize("hasRole('admin')")
	public ResponseEntity<List<BusOperator>> getAllOperators(Principal principal){
		
		return new ResponseEntity<>(services.busOperatorService.viewAllBusOperators(), HttpStatus.OK);
	}
}
