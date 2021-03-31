package com.techleads.app.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Service;

import com.techleads.app.DBQueries;
import com.techleads.app.dao.LoginDetailsDAO;
import com.techleads.app.model.LoginDetails;

@Service
public class LoginDetailsService {

	private LoginDetailsDAO loginDetailsDAO;

	public LoginDetailsService(LoginDetailsDAO loginDetailsDAO) {
		this.loginDetailsDAO = loginDetailsDAO;
	}

	public List<LoginDetails> findAll(String today) {

		List<LoginDetails> logins = new ArrayList<>();

		logins = loginDetailsDAO.findAll(today);
		if (logins.size() > 0) {

			return logins;
		}
		return logins;
	}

	public String saveLoginDetails(LoginDetails login) {
		login.setLoginDate(new Date());
		
		getActualShift(login);
		
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
	private void getActualShift(LoginDetails login) {
		if(login.getEmpId()==101) {
			login.setActualShift(DBQueries.SHIFT_101);
		}else if(login.getEmpId()==102) {
			login.setActualShift(DBQueries.SHIFT_102);
		}else if(login.getEmpId()==103) {
			login.setActualShift(DBQueries.SHIFT_103);
		}else if(login.getEmpId()==104) {
			login.setActualShift(DBQueries.SHIFT_104);
		}else if(login.getEmpId()==105) {
			login.setActualShift(DBQueries.SHIFT_105);
		}else if(login.getEmpId()==106) {
			login.setActualShift(DBQueries.SHIFT_106);
		}
	}

		
}
