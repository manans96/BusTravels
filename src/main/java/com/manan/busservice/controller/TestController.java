package com.manan.busservice.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(path = "/test")
public class TestController {
	
	@PostMapping(path = "/add")
	public void setUser() {
		
	}

}
