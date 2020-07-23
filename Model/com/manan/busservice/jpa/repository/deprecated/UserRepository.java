package com.manan.busservice.jpa.repository.deprecated;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.manan.busservice.model.user.UserEntity;

@Deprecated
public interface UserRepository extends JpaRepository<UserEntity, Integer> {
	
	Optional<UserEntity> findByUserName(String userName);

}
