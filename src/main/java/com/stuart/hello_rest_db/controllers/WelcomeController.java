package com.stuart.hello_rest_db.controllers;

import java.util.Map;

import com.stuart.hello_rest_db.modul.Product;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import springfox.documentation.annotations.ApiIgnore;

@Controller
@ApiIgnore
public class WelcomeController {

	@RequestMapping("/")
	public String welcome() {
		return "index";
	}

	@RequestMapping("/api")
	public String showApiInfo(){
		return "redirect:/swagger-ui.html";
	}
}