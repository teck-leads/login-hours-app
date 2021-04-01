package com.techleads.app.service;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.techleads.app.dao.LoginDao;
import com.techleads.app.model.Login;
import com.techleads.app.model.LoginDetails;

@Service
public class LoginService {

	private LoginDao loginDao;
	private LoginDetailsService loginDetailsService;
	
	
	@Autowired
	public void setLoginDetailsService(LoginDetailsService loginDetailsService) {
		this.loginDetailsService = loginDetailsService;
	}

	@Autowired
	public LoginService(LoginDao loginDao) {
		this.loginDao = loginDao;
	}

	public List<Login> findAll() {

		List<Login> logins = new ArrayList<>();
		
		logins = loginDao.findAll();
		if (logins.size() > 0) {
			List<LoginDetails> loginDtls = loginDetailsService.findAll(null);
			logins.forEach(log->{
				List<LoginDetails> collect = loginDtls.stream().filter(dtl->dtl.getEmpId().equals(log.getId())).collect(Collectors.toList());
				log.setLogDetails(collect);
			});
			return logins;
		}
		logins.add(new Login());
		return logins;
	}

	public String saveEmpLogin(Login login) {
		return loginDao.save(login);

	}
	
	public Login findEmpLoginById(Integer id) {

		Login login = loginDao.findById(id);
		if (login != null) {
			return login;
		}
		return new Login();
	}
	
	public String updateEmpLoginById(Login login) {
		String status = loginDao.updateById(login);
		return status;
	}
	
	public String deleteEmpLoginById(Integer id) {
		String status = loginDao.deleteById(id);
		return status;
		
	}
}
