package com.demo.spring;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.demo.spring.entity.Post;

@Component
public class TestClientRunner implements CommandLineRunner {

	@Autowired
	JsonPlaceholderClient client;
	
	@Autowired
	EmpServiceClients empClient;
	@Override
	public void run(String... args) throws Exception {
		Post post=client.findPostById(10);
		
		System.out.println(post);
		
		System.out.println(empClient.findEmp(101));

	}

}
