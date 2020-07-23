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
import com.manan.busservice.dto.model.operator.Trip;
import com.manan.busservice.dto.model.user.User;
import com.manan.busservice.jpa.repository.Repositories;
import com.manan.busservice.model.operations.BookingEntity;
import com.manan.busservice.utility.DateUtils;

/**
 * @author Manan Sanghvi
 *
 */
@Component
public class BookingServiceImpl implements BookingService {
	
	private Repositories.Container repos;
	
	@Autowired
	public BookingServiceImpl(Repositories.Container repos) {
		this.repos = repos;
	}
	
	private Optional<BookingEntity> optional;
	
	private void findByBookingCode(Booking booking) {
		optional = repos.bookingRepository.findByBookingCode(booking.getBookingCode());
	}

	@Override
	public Booking newBooking(Booking booking) {

		findByBookingCode(booking);
		if(optional.isEmpty()) {
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
		} else {
			return new Booking();
		}
	}

	@Override
	public Booking editBooking(Booking booking) {

		findByBookingCode(booking);
		if(optional.isPresent()) {
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
		} else {
			return new Booking();
		}
	}

	@Override
	public void cancelBooking(Booking booking) {

		findByBookingCode(booking);
		if(optional.isPresent()) {
			BookingEntity bookingEntity = optional.get();
			BookingMapper.toBooking(repos.bookingRepository.save(bookingEntity
					.setCancelled(true)));
		}
	}

	@Override
	public Booking viewBooking(Booking booking) {

		findByBookingCode(booking);
		if(optional.isPresent()) {
			return BookingMapper.toBooking(optional.get());
		} else {
			return new Booking();
		}
	}

	@Override
	public List<Booking> viewAllBookingByUser(User user) {

		return BookingMapper.toBooking(repos.bookingRepository.findByPassenger(repos.userRepository.findByUserName(user.getUserName()).get()));
	}

	@Override
	public List<Booking> viewAllBookings() {

		return BookingMapper.toBooking(repos.bookingRepository.findAll());
	}

	@Override
	public List<Booking> viewBookingsByTrip(Trip trip) {

		return BookingMapper.toBooking(repos.bookingRepository.findByTripCode(repos.tripRepository.findByCode(trip.getCode())
				.get()));
	}

}
