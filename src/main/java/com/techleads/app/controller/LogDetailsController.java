package com.techleads.app.controller;

import java.util.ArrayList;
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

import com.techleads.app.model.LoginDetails;
import com.techleads.app.service.LoginDetailsService;

@RestController
public class LogDetailsController {

	private LoginDetailsService loginDetailsService;

	public LogDetailsController(LoginDetailsService loginDetailsService) {
		this.loginDetailsService = loginDetailsService;
	}

	@GetMapping(value = "/logindtls", produces = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE,
			MediaType.TEXT_PLAIN_VALUE })
	public ResponseEntity<List<LoginDetails>> findAllLoginDetails() {
		List<LoginDetails> logins = loginDetailsService.findAll(null);
		if (logins.size() > 0) {
			return new ResponseEntity<>(logins, HttpStatus.OK);
		} else {
			logins = new ArrayList<>();
			logins.add(new LoginDetails());
			return new ResponseEntity<>(logins, HttpStatus.OK);
		}
	}
	
	
	@GetMapping(value = "/logindtls/{today}", produces = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE,
			MediaType.TEXT_PLAIN_VALUE })
	public ResponseEntity<List<LoginDetails>> findAllTodayLoginDetails(@PathVariable("today") String today) {
		List<LoginDetails> logins = loginDetailsService.findAll(today);
		if (logins.size() > 0) {
			return new ResponseEntity<>(logins, HttpStatus.OK);
		} else {
			logins = new ArrayList<>();
			logins.add(new LoginDetails());
			return new ResponseEntity<>(logins, HttpStatus.OK);
		}
	}
	

	@PostMapping(value = "/logindtls", consumes = {MediaType.APPLICATION_JSON_VALUE})
	public ResponseEntity<String> saveLoginDetails(@RequestBody LoginDetails login) {
		String result = loginDetailsService.saveLoginDetails(login);
		return new ResponseEntity<>(result, HttpStatus.OK);
	}

	@GetMapping(value = "/logindtls/{id}")
	public ResponseEntity<LoginDetails> findLoginDetailsById(@PathVariable("id") Integer id) {
		LoginDetails loginDtls = loginDetailsService.findLoginDetailsById(id);
		return new ResponseEntity<>(loginDtls, HttpStatus.OK);
	}
	
	@PutMapping(value = "/logindtls", consumes = {MediaType.APPLICATION_JSON_VALUE} )
	public ResponseEntity<String> updateLoginDetailsById(@RequestBody LoginDetails login) {
		String status = loginDetailsService.updateLoginDetailsById(login);
		return new ResponseEntity<>(status, HttpStatus.OK);
	}
	
	
	
	@DeleteMapping(value = "/logindtls/{id}")
	public ResponseEntity<String> deleteLoginDetailsById(@PathVariable("id") Integer id) {
		String status = loginDetailsService.deleteLoginDetailsById(id);
		return new ResponseEntity<>(status, HttpStatus.OK);
	}

}
