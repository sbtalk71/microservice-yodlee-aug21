package com.demo.spring.entity;

import java.util.HashMap;

import org.springframework.stereotype.Repository;

@Repository
public class EmpDao {

	public HashMap<Integer, Emp> db=new HashMap<>();

	public EmpDao() {
		db.put(100, new Emp(100, "Pavan", "Hyderabad", 65000));
		db.put(101, new Emp(101, "Charan", "Hyderabad", 55000));
		db.put(102, new Emp(102, "John", "Chennai", 45000));
		db.put(103, new Emp(103, "Kiran", "Pune", 75000));
		db.put(104, new Emp(104, "Ankit", "Hyderabad", 75000));
		db.put(105, new Emp(105, "Chandra", "Bangalore", 65000));
	}

	public HashMap<Integer, Emp> getDb() {
		return db;
	}

	public void setDb(HashMap<Integer, Emp> db) {
		this.db = db;
	}
	
	
	
}
