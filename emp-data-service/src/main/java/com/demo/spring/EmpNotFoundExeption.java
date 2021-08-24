package com.demo.spring;

public class EmpNotFoundExeption extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public EmpNotFoundExeption() {
		// TODO Auto-generated constructor stub
	}
	
	public EmpNotFoundExeption(String message) {
		super(message);
	}
	
}
