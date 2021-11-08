package com.jbc.swagger;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import springfox.documentation.annotations.ApiIgnore;

/*Swagger redirections*/
@ApiIgnore
@Controller
public class HomeController {

	@RequestMapping(value = "/swagger")
	public String index() {
		return "redirect:swagger-ui/";
	}
	
}
