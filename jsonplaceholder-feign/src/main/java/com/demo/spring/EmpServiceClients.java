package com.demo.spring;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.demo.spring.entity.Emp;

@FeignClient(url = "http://localhost:8081",name="emp-service")
public interface EmpServiceClients {

	@GetMapping(path="emp/find/{id}",produces = MediaType.APPLICATION_JSON_VALUE)
	ResponseEntity findEmp(@PathVariable("id") int id);

	@GetMapping(path="emp",produces = MediaType.APPLICATION_JSON_VALUE)
	ResponseEntity<List<Emp>> list();

	@PostMapping(path="emp/save",produces = MediaType.TEXT_PLAIN_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	ResponseEntity<String> saveEmp(@RequestBody Emp e);

}