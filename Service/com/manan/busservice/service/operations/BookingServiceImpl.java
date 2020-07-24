/**
 * 
 */
package com.manan.busservice.service.operations;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.manan.busservice.dto.mapper.operations.BookingMapper;
import com.manan.busservice.dto.model.operations.Booking;
import com.manan.busservice.exception.BusAppException;
import com.manan.busservice.jpa.repository.Repositories;
import com.manan.busservice.model.operations.BookingEntity;
import com.manan.busservice.response.ResponseEntity;
import com.manan.busservice.service.Services;
import com.manan.busservice.utility.DateUtils;

/**
 * @author Manan Sanghvi
 *
 */
@Component
public class BookingServiceImpl implements Services.BookingService {
	
	private Repositories.Container repos;
	
	@Autowired
	public BookingServiceImpl(Repositories.Container repos) {
		this.repos = repos;
	}
	
	private Optional<BookingEntity> optional;
	
	private void findByBookingCode(String bookingCode) {
		optional = repos.bookingRepository.findByBookingCode(bookingCode);
	}

	@Override
	public Booking newBooking(Booking booking) {

		findByBookingCode(booking.getBookingCode());
		if(optional.isEmpty()) {
			try {
				return BookingMapper.toBooking(repos.bookingRepository.save(new BookingEntity()
						.setBookingCode(booking.getBookingCode())
						.setCancelled(false)
						.setDepartureTime(booking.getDepartureTime())
						.setLastUpdate(DateUtils.today())
						.setTotalCost(booking.getTotalCost())
						.setBus(repos.busRepository.findByBusCode(booking.getBus().getBusCode())
								.get())
						.setTripCode(repos.tripRepository.findByCode(booking.getTripCode().getCode())
								.get())
						.setPassenger(repos.userRepository.findByUserName(booking.getPassenger().getUserName())
								.get())));
			} catch(RuntimeException re) {
				throw new BusAppException.BadRequestException(ResponseEntity.BOOKING);
			}
		}
		throw new BusAppException.DuplicateEntityException(ResponseEntity.BOOKING);
	}

	@Override
	public Booking editBooking(Booking booking) {

		findByBookingCode(booking.getBookingCode());
		if(optional.isPresent()) {
			try {
				BookingEntity bookingEntity = optional.get();
				return BookingMapper.toBooking(repos.bookingRepository.save(bookingEntity
						.setDepartureTime(booking.getDepartureTime())
						.setLastUpdate(DateUtils.today())
						.setTotalCost(booking.getTotalCost())
						.setTripCode(repos.tripRepository.findByCode(booking.getTripCode().getCode())
								.get())
						.setBus(repos.busRepository.findByBusCode(booking.getBus().getBusCode())
								.get())
						));
			} catch(RuntimeException re) {
				throw new BusAppException.BadRequestException(ResponseEntity.BOOKING);
			}
		}
		throw new BusAppException.EntityNotFoundException(ResponseEntity.BOOKING);
	}

	@Override
	public void cancelBooking(String bookingCode) {

		findByBookingCode(bookingCode);
		if(optional.isPresent()) {
			BookingEntity bookingEntity = optional.get();
			BookingMapper.toBooking(repos.bookingRepository.save(bookingEntity
					.setCancelled(true)));
		}
		throw new BusAppException.EntityNotFoundException(ResponseEntity.BOOKING);
	}

	@Override
	public Booking viewBooking(String bookingCode) {

		findByBookingCode(bookingCode);
		if(optional.isPresent()) {
			return BookingMapper.toBooking(optional.get());
		}
		throw new BusAppException.EntityNotFoundException(ResponseEntity.BOOKING);
	}

	@Override
	public List<Booking> viewAllBookingByUser(String userName) {

		return BookingMapper.toBooking(repos.bookingRepository.findByPassenger(repos.userRepository.findByUserName(userName).get()));
	}

	@Override
	public List<Booking> viewAllBookings() {

		return BookingMapper.toBooking(repos.bookingRepository.findAll());
	}

	@Override
	public List<Booking> viewBookingsByTrip(String tripCode) {

		return BookingMapper.toBooking(repos.bookingRepository.findByTripCode(repos.tripRepository.findByCode(tripCode)
				.get()));
	}

}
