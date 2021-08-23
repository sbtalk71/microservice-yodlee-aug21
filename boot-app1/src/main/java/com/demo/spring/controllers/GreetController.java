package com.demo.spring.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@PropertySource({"app.properties"})
public class GreetController {
private static Logger LOGGER= LoggerFactory.getLogger(GreetController.class);
	
@Value("${class_name:no-class}")
	String c_name;
	@RequestMapping(path="/greet/{name}",method = RequestMethod.GET)
	public String greetAction(@PathVariable("name") String user) {
		LOGGER.debug("inside greet action with name {}",user);
		return "Good After Noon "+user+ "in "+c_name;
	}
	
	@RequestMapping(path="/greet",method = RequestMethod.GET)
	public String greetAction2(@RequestParam("name") String user) {
		return "Good After Noon "+user;
	}
}
