package com.hashfold.lss.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class PathController {

	@RequestMapping("/login")
	public String list() {
		return "loginPage";
	}
}
