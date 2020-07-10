package com.manan.busservice.dto.mapper.user;

import com.manan.busservice.dto.mapper.operations.BookingMapper;
import com.manan.busservice.dto.mapper.operations.TicketMapper;
import com.manan.busservice.dto.mapper.operator.BusOperatorMapper;
import com.manan.busservice.dto.model.user.User;
import com.manan.busservice.model.user.UserEntity;

public class UserMapper {
	
	public static User toUser(UserEntity user) {
		
		return new User()
				.setUserName(user.getUserName())
				.setFirstName(user.getFirstName())
				.setLastName(user.getLastName())
				.setEmail(user.getEmail())
				.setPhoneNo(user.getPhoneNo())
				.setRole(user.getRole())
				.setUserAuth(UserAuthMapper.toUserAuth(user.getUserAuth()))
				.setTicket(TicketMapper.toTicket(user.getTicket()))
				.setBooking(BookingMapper.toBooking(user.getBooking()))
				.setOperator(BusOperatorMapper.toBusOperator(user.getOperator()));
		
	}

}
