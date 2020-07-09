package com.manan.busservice.jpa.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.manan.busservice.model.user.User;

public interface UserRepository extends JpaRepository<User, Integer> {

}