package com.kk.test.controller;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class TestController {

	// get log4j handler
	private static final Logger logger = Logger.getLogger(TestController.class);
	
	@RequestMapping(value="/", method=RequestMethod.GET)
	public @ResponseBody String testMain(){
		
		logger.debug("logginggggggggggggggggggggg");
		
		 return "hihihihi";
	}
	
	@RequestMapping(value="/http-basic-test", method=RequestMethod.GET)
	public @ResponseBody String httpBasicTest(){
		return "sccessful";
	}
	
}
