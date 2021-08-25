package com.demo.spring;

import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.demo.spring.entity.Emp;
import com.demo.spring.repository.EmpRepository;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;

@RestController
public class EmpRestController{

	@Autowired
	private EmpRepository repo;
	

	@GetMapping(path="emp/find/{id}",produces = MediaType.APPLICATION_JSON_VALUE)
	@HystrixCommand(fallbackMethod = "fallbackFindEmp")
	public ResponseEntity findEmp(@PathVariable("id")int id,HttpServletRequest req) {
		System.out.println("served by : "+req.getLocalAddr()+":"+req.getLocalPort());
		Optional<Emp> empOp=repo.findById(id);
		
		if(empOp.isPresent()) {
			return ResponseEntity.ok(empOp.get());
		}else {
			//return ResponseEntity.status(404).body("Emp is not found..");
			throw new EmpNotFoundExeption("Emp not found..");
		}
		
	}
	
	
	
	@GetMapping(path="emp",produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<Emp>> list() {
		return ResponseEntity.ok(repo.findAll());
		
	}
	
	
	@PostMapping(path="emp/save",produces = MediaType.TEXT_PLAIN_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<String> saveEmp(@RequestBody Emp e){
		
		if(repo.existsById(e.getEmpId())) {
			return ResponseEntity.ok("Emp already exists..");
		}else {
			repo.save(e);
			return ResponseEntity.ok("Emp saved..");
		}
	}
	
	
	
	
	//Exception handlers
	@ExceptionHandler(EmpNotFoundExeption.class)
	public ResponseEntity handleException(EmpNotFoundExeption ex) {
		return ResponseEntity.status(404).body("Emp is not found..");
	}
	
	
	//fallback mathods
	public ResponseEntity fallbackFindEmp(int id,HttpServletRequest req) {
		return ResponseEntity.ok("EMp data service is unavailable.. Try after sometime");
	}
}
