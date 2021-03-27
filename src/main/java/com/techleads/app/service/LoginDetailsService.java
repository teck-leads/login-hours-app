package com.techleads.app.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import org.springframework.stereotype.Service;

import com.techleads.app.dao.LoginDetailsDAO;
import com.techleads.app.model.LoginDetails;

@Service
public class LoginDetailsService {

	private LoginDetailsDAO loginDetailsDAO;

	public LoginDetailsService(LoginDetailsDAO loginDetailsDAO) {
		this.loginDetailsDAO = loginDetailsDAO;
	}

	public List<LoginDetails> findAll() {

		List<LoginDetails> logins = new ArrayList<>();

		logins = loginDetailsDAO.findAll();
		if (logins.size() > 0) {

			return logins;
		}
		return logins;
	}

	public String saveLoginDetails(LoginDetails login) {
		login.setLoginDate(new Date());
		return loginDetailsDAO.save(login);

	}

	public LoginDetails findLoginDetailsById(Integer id) {

		LoginDetails loginDtls = loginDetailsDAO.findById(id);
		if (loginDtls != null) {
			return loginDtls;
		}
		return new LoginDetails();
	}
	
	public String updateLoginDetailsById(LoginDetails logDtls) {
		String status = loginDetailsDAO.updateById(logDtls);
		return status;
	}
	
	public String deleteLoginDetailsById(Integer id) {
		String status = loginDetailsDAO.deleteById(id);
		return status;
		
	}
		
}
