package com.demo.spring;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.demo.spring.entity.Post;



@FeignClient(url = "https://jsonplaceholder.typicode.com",name = "myclient")
public interface JsonPlaceholderClient {

	@GetMapping(path="/posts/{id}",produces = MediaType.APPLICATION_JSON_VALUE)
	public Post findPostById(@PathVariable("id")int id);
}
