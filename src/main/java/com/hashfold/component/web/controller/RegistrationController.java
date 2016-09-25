package com.hashfold.component.web.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.hashfold.component.model.user.User;
import com.hashfold.component.service.IUserService;
import com.hashfold.component.validation.EmailExistsException;

@Controller
class RegistrationController {

	@Autowired
	private IUserService userService;

	//

	@RequestMapping(value = "signup")
	public ModelAndView registrationForm() {
		return new ModelAndView("registrationPage", "user", new User());
	}

	@RequestMapping(value = "user/register")
	public ModelAndView registerUser(@Valid final User user, final BindingResult result) {
		if (result.hasErrors()) {
			return new ModelAndView("registrationPage", "user", user);
		}
		try {
			userService.registerNewUser(user);
		} catch (EmailExistsException e) {
			result.addError(new FieldError("user", "email", e.getMessage()));
			return new ModelAndView("registrationPage", "user", user);
		}
		return new ModelAndView("redirect:/login");
	}

}