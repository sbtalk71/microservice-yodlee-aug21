package com.demo.spring.clients;

import org.springframework.web.client.RestTemplate;

public class RestClient1 {

	public static void main(String[] args) {
		RestTemplate rt = new RestTemplate();
		String resp=rt.getForObject("http://localhost:8081/emp/find/104", String.class);
		System.out.println(resp);

	}

}
