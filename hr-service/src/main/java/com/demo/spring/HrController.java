package com.demo.spring;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;

@RestController
public class HrController {

	@Autowired
	RestTemplate rt;

	@GetMapping(path = "/hr/details")
	@HystrixCommand(fallbackMethod = "fallBackGetEmpDetails",commandProperties = {
			@HystrixProperty(name = "circuitBreaker.sleepWindowInMilliseconds", value = "60000")
	})
	public ResponseEntity<String> getEmpDetails(@RequestParam("empId") int empId) {

		HttpHeaders headers = new HttpHeaders();
		headers.set("Accept", MediaType.APPLICATION_JSON_VALUE);

		HttpEntity req = new HttpEntity<>(headers);

		ResponseEntity<String> resp = rt.exchange("http://emp-service/emp/find/" + empId, HttpMethod.GET, req,
				String.class);
		return resp;
	}
	
	
	public ResponseEntity<String> fallBackGetEmpDetails(int empId) {
		return ResponseEntity.ok("Service Unavailable, Try after sometime..");
	}
}
