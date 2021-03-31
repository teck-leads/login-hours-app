package com.techleads.app.controller;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.techleads.app.model.Customer;

@RestController
public class CustomerController {
	
	
	@PutMapping(value = {"/cust"}, consumes = {"appplication/xml", "application/json"},
			produces = {"text/plain"}
	)
	public String updateCustomer(@RequestBody Customer cust) {
		
		if(cust.getId()>0) {
			return "Customer updated successfully";
		}else {
			return "No Customer found";
		}
	}
	@DeleteMapping(value = {"cust/{id}"})
	public String deleteCustomer(@PathVariable("id") Integer id) {
		
		if(id>0) {
			return "Customer deleted successfully";
		}else {
			return "No Customer found with "+id;
		}
		
	}

}
