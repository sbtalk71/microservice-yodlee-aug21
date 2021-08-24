package com.demo.spring;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.demo.spring.entity.Emp;
import com.demo.spring.entity.EmpDao;

@RestController
public class EmpController {
@Autowired
EmpDao dao;
	//@RequestMapping(path = "emp/find/{id}", method = RequestMethod.GET, produces = {MediaType.APPLICATION_JSON_VALUE,MediaType.APPLICATION_XML_VALUE})
	@GetMapping(path = "emp/find/{id}", produces = {MediaType.APPLICATION_JSON_VALUE,MediaType.APPLICATION_XML_VALUE})
	public Emp findEmp(@PathVariable("id") int empId) {
		if (dao.getDb().containsKey(empId)) {
			return dao.getDb().get(empId);
		} else {
			throw new RuntimeException("Emp Not Found");
		}
	}
	
	
	@GetMapping(path = "/", produces = {MediaType.APPLICATION_JSON_VALUE})
	public List<Emp> listAll() {
		List<Emp> empList = new ArrayList<>(dao.getDb().values());
		return empList;
	}
}
