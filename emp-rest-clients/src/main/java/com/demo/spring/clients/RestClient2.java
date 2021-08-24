package com.demo.spring.clients;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import com.demo.spring.entity.Emp;

public class RestClient2 {

	public static void main(String[] args) {
		RestTemplate rt = new RestTemplate();
		HttpHeaders headers = new HttpHeaders();
		headers.set("Accept", MediaType.APPLICATION_JSON_VALUE);

		HttpEntity req = new HttpEntity<>(headers);

		ResponseEntity<String> resp = rt.exchange("http://localhost:8081/emp/find/104", HttpMethod.GET, req,
				String.class);

		System.out.println(resp.getBody());

		ResponseEntity<Emp> resp1 = rt.exchange("http://localhost:8081/emp/find/103", HttpMethod.GET, req, Emp.class);

		System.out.println(resp1.getBody());

	}

}
