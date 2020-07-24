package com.manan.busservice.controller;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.manan.busservice.dto.model.operations.Booking;
import com.manan.busservice.dto.model.operations.Stop;
import com.manan.busservice.dto.model.operations.Ticket;
import com.manan.busservice.dto.model.operations.TripDetails;
import com.manan.busservice.dto.model.operator.Bus;
import com.manan.busservice.dto.model.operator.BusOperator;
import com.manan.busservice.dto.model.operator.Trip;
import com.manan.busservice.dto.model.user.User;
import com.manan.busservice.dto.model.user.UserAuth;
import com.manan.busservice.service.Services;
import com.manan.busservice.utility.DateUtils;

@RestController
@RequestMapping("/test")
public class TestController {

	private Services.Container services;
	
	@Autowired
	public TestController(Services.Container services) {
		this.services = services;
	}
	
	@PostMapping("/add")
	public ResponseEntity<User> register() {
		
		User user = new User()
				.setFirstName("Manan")
				.setLastName("Sanghvi")
				.setUserName("test96")
				.setEmail("mks8328@rthgire")
				.setPhoneNo("24646464848")
				.setRole("admin");
		
		UserAuth userAuth = new UserAuth()
				.setPassword("neiuewn48fi")
				.setLastUpdate(DateUtils.today());
		
		return new ResponseEntity<>(services.userService.signup(user, userAuth), HttpStatus.OK);
		
//		userRepository.save(user);
			
	}
	
	@GetMapping("/get")
	public @ResponseBody User getUser() {

		return services.userService.login("test96" , "neiuewn48fi ");
//		return busOperatorService.viewBusOperator("AAC123");
//		return busService.viewAllBusByOperator(new BusOperator().setOperatorCode("AAC123"));
	}
	
	@PostMapping("/addoperator")
	public void newOperator() {
		
		User operator = services.userService.findUser("test96");
		
		BusOperator busOperator = new BusOperator()
				.setOperator(operator)
				.setOperatorCode("AAC123")
				.setOperatorDetails("Best Service!")
				.setOperatorName("MyOperator");
		
		services.busOperatorService.addNewOperator(busOperator);
		
	}
	
	@PostMapping("/addbus")
	public void newBus() {
		
		BusOperator busOperator = services.busOperatorService.viewBusOperator("AAC123");
		
		Bus bus = new Bus()
				.setBusCode("JAS123")
				.setBusModel("Mercedes")
				.setCapacity(20)
				.setHaltCost(4)
				.setRunCost(20)
				.setAvailable(true)
				.setOperator(busOperator);
		
		services.busService.addBus(bus);
		
	}
	
	@PostMapping("/addtrip")
	public void newTrip() {
		
		BusOperator busOperator = services.busOperatorService.viewBusOperator("AAC123");
		
		Trip trip = new Trip()
				.setCode("TRIP1")
				.setArriveCode("PUN")
				.setDepartCode("MUM")
				.setBusOperator(busOperator)
				.setHaltStop1("LON")
				.setHaltTime(15)
				.setJourneyTime(120);
		
		services.tripService.addTrip(trip);
		
	}
	
	@PostMapping("/addstop")
	public @ResponseBody Stop newStop() {
		
		Stop stop = new Stop()
				.setStopCode("LON")
				.setStopName("Lonavla")
				.setStopType("minor");
		
		services.stopService.addStop(stop);
		return services.stopService.findStop(stop.getStopCode());
	}
	
	@PostMapping("/addbooking")
	public @ResponseBody Booking newBooking() {
		
		Booking booking = new Booking()
				.setBus(new Bus().setBusCode("JAS123"))
				.setBookingCode("BOOK789")
				.setPassenger(new User().setUserName("test96"))
				.setTripCode(new Trip().setCode("TRIP1"))
				.setTotalCost(40000)
				.setDepartureTime(new Date(1596110500000L));
		
		return services.bookingService.newBooking(booking);
	}
	
	@GetMapping("/getbooking")
	public @ResponseBody Booking getBooking() {
		
		return services.bookingService.viewBooking("BOOK123");
	}
	
	@PostMapping("/addtripdetail")
	public @ResponseBody TripDetails addTripDetails() {
		
		TripDetails tripDetails = new TripDetails()
				.setAvailableSeats(40)
				.setCost(500)
				.setDepartureTime(new Date(1596110500000L))
				.setTripDetailCode("TRIPDET123")
				.setTripCode(services.tripService.viewTrip("TRIP1"))
				.setBus(services.busService.viewBus("JAS123"));
		
		return services.tripDetailService.addNewJourney(tripDetails);
	}
	
	@GetMapping("/gettripdetail")
	public @ResponseBody TripDetails getTripDetails() {
		
		return services.tripDetailService.viewTrip("TRIPDET123");
	}
	
	@PostMapping("/addticket")
	public @ResponseBody Ticket addTicket() {
		
		Ticket ticket = new Ticket()
				.setAmountPaid(500)
				.setCancellable(true)
				.setTicketNumber("TICKET123")
				.setTotalTicket(2)
				.setPassenger(services.userService.findUser("test96"))
				.setTripDetails(services.tripDetailService.viewTrip("TRIPDET123"));
		
		return services.ticketService.newTicket(ticket);
	}
	
	@GetMapping("/getticket")
	public @ResponseBody Ticket getTicket() {
		
		return services.ticketService.viewTicket("TICKET123");
	}

}
