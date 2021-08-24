package com.demo.spring.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.demo.spring.entity.Emp;

public interface EmpRepository extends JpaRepository<Emp, Integer>{

}
