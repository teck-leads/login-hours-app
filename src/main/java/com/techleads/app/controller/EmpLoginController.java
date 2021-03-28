package com.techleads.app.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.techleads.app.model.Login;
import com.techleads.app.service.LoginService;

@RestController
public class EmpLoginController {
	
	private LoginService loginService;

	public EmpLoginController(LoginService loginService) {
		this.loginService = loginService;
	}
	
	@PostMapping(value = "/emplogins", consumes = {MediaType.APPLICATION_JSON_VALUE})
	public ResponseEntity<String> saveLoginDetails(@RequestBody Login login) {
		String result = loginService.saveEmpLogin(login);
		return new ResponseEntity<>(result, HttpStatus.CREATED);
	}

	@GetMapping(value = "/emplogins", produces = { MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<List<Login>> findAllLoginDetails() {
		List<Login> logins = loginService.findAll();
			return new ResponseEntity<>(logins, HttpStatus.OK);
		
	}
	
	@GetMapping(value = "/emplogins/{id}")
	public ResponseEntity<Login> findLoginDetailsById(@PathVariable("id") Integer id) {
		Login login = loginService.findEmpLoginById(id);
		return new ResponseEntity<>(login, HttpStatus.OK);
	}
	
	@PutMapping(value = "/emplogins", consumes = {MediaType.APPLICATION_JSON_VALUE} )
	public ResponseEntity<String> updateEmpLoginById(@RequestBody Login login) {
		String status = loginService.updateEmpLoginById(login);
		return new ResponseEntity<>(status, HttpStatus.OK);
	}
	
	@DeleteMapping(value = "/emplogins/{id}")
	public ResponseEntity<String> deleteEmpLoginById(@PathVariable("id") Integer id) {
		String status = loginService.deleteEmpLoginById(id);
		return new ResponseEntity<>(status, HttpStatus.OK);
	}

}
