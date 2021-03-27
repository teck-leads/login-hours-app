package com.techleads.app.dao;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.techleads.app.model.LoginDetails;

@Repository
public class LoginDetailsDAO {

	List<LoginDetails> logins = new ArrayList<>();

	public List<LoginDetails> findAll() {
		return logins;
	}

	public String save(LoginDetails login) {
		logins.add(login);
		return "Login Details saved successfully with Id: " + login.getId();

	}

	public LoginDetails findById(Integer id) {

		for (LoginDetails login : logins) {
			if (login.getId() == id) {
				return login;
			}
		}
		return null;
	}

	public String updateById(LoginDetails logDtls) {
		boolean flag = false;
		for (LoginDetails login : logins) {
			if (login.getId() == logDtls.getId()) {

				login.setActualShift(logDtls.getActualShift());
				login.setLoginTime(logDtls.getLoginTime());
				login.setLogOffTime(logDtls.getLogOffTime());
				login.setComments(logDtls.getComments());

				flag = true;
			}
		}
		return flag == true ? ("Updated record with id " + logDtls.getId()) : " Not updated ";
	}
	
	public String deleteById(Integer id) {
		boolean flag = false;
		Iterator<LoginDetails> itr = logins.iterator();
		while(itr.hasNext()) {
			LoginDetails logDtls = itr.next();
			if(logDtls.getId()==id){
				itr.remove();
				flag=true;
			}
		}
		
		return flag == true ? ("Deleted record with id " + id ): " Not Deleted ";
		
	}

}
