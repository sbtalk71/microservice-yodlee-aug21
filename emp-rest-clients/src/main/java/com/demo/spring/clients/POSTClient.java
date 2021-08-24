package com.demo.spring.clients;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import com.demo.spring.entity.Emp;

public class POSTClient {

	public static void main(String[] args) {
		RestTemplate rt = new RestTemplate();
		HttpHeaders headers = new HttpHeaders();
		headers.set("Accept", MediaType.TEXT_PLAIN_VALUE);
		headers.set("Content-Type", MediaType.APPLICATION_JSON_VALUE);
		
		Emp emp= new Emp(106, "Amit", "Noida", 78000);

		HttpEntity req = new HttpEntity<>(emp,headers);

		ResponseEntity<String> resp = rt.exchange("http://localhost:8081/emp/save", HttpMethod.POST, req,
				String.class);

		System.out.println(resp.getBody());

		

	}

}
