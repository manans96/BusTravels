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
import com.manan.busservice.jpa.repository.BookingRepository;
import com.manan.busservice.jpa.repository.BusRepository;
import com.manan.busservice.jpa.repository.TripRepository;
import com.manan.busservice.jpa.repository.UserRepository;
import com.manan.busservice.model.operations.BookingEntity;
import com.manan.busservice.utility.DateUtils;

/**
 * @author Manan Sanghvi
 *
 */
@Component
public class BookingServiceImpl implements BookingService {
	
	private BookingRepository bookingRepository;
	private UserRepository userRepository;
	private TripRepository tripRepository;
	private BusRepository busRepository;
	
	@Autowired
	public BookingServiceImpl(BookingRepository bookingRepository, UserRepository userRepository, TripRepository tripRepository, BusRepository busRepository) {
		this.bookingRepository = bookingRepository;
		this.userRepository = userRepository;
		this.tripRepository = tripRepository;
		this.busRepository = busRepository;
	}
	
	private Optional<BookingEntity> optional;
	
	private void findByBookingCode(Booking booking) {
		optional = bookingRepository.findByBookingCode(booking.getBookingCode());
	}

	@Override
	public Booking newBooking(Booking booking) {

		findByBookingCode(booking);
		if(optional.isEmpty()) {
			return BookingMapper.toBooking(bookingRepository.save(new BookingEntity()
					.setBookingCode(booking.getBookingCode())
					.setCancelled(false)
					.setDepartureTime(booking.getDepartureTime())
					.setLastUpdate(DateUtils.today())
					.setTotalCost(booking.getTotalCost())
					.setBus(busRepository.findByBusCode(booking.getBus().getBusCode())
							.get())
					.setTripCode(tripRepository.findByCode(booking.getTripCode().getCode())
							.get())
					.setPassenger(userRepository.findByUserName(booking.getPassenger().getUserName())
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
			return BookingMapper.toBooking(bookingRepository.save(bookingEntity
					.setDepartureTime(booking.getDepartureTime())
					.setLastUpdate(DateUtils.today())
					.setTotalCost(booking.getTotalCost())
					.setTripCode(tripRepository.findByCode(booking.getTripCode().getCode())
							.get())
					.setBus(busRepository.findByBusCode(booking.getBus().getBusCode())
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
			BookingMapper.toBooking(bookingRepository.save(bookingEntity
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

		return BookingMapper.toBooking(bookingRepository.findByPassenger(userRepository.findByUserName(user.getUserName()).get()));
	}

	@Override
	public List<Booking> viewAllBookings() {

		return BookingMapper.toBooking(bookingRepository.findAll());
	}

	@Override
	public List<Booking> viewBookingsByTrip(Trip trip) {

		return BookingMapper.toBooking(bookingRepository.findByTripCode(tripRepository.findByCode(trip.getCode())
				.get()));
	}

}
