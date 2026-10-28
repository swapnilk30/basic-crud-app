package com.example.external.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/view")
public class TestViewController {
	
	@GetMapping("/home")
	public String testView() {
		return "home";
	}

}
