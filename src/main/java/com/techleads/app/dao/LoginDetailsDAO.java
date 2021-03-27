package com.techleads.app.dao;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.techleads.app.DBQueries;
import com.techleads.app.model.LoginDetails;

@Repository
public class LoginDetailsDAO {
	
	private JdbcTemplate jdbcTemplate;

	@Autowired
	public LoginDetailsDAO(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

	List<LoginDetails> logins = new ArrayList<>();

	public List<LoginDetails> findAll() {
		List<LoginDetails> loginDtls = this.jdbcTemplate.query(
				DBQueries.SELECT_ALL_LOG_DTLS,
		        (resultSet, rowNum) -> {
		        	LoginDetails log = new LoginDetails();
		        	log.setId(resultSet.getInt("ID"));
		        	log.setName(resultSet.getString("NAME"));
		        	log.setLoginDate(resultSet.getDate("LOGIN_DTE"));
		        	log.setActualShift(resultSet.getString("ACTUAL_SHIFT"));
		        	log.setLoginTime(resultSet.getString("LOGIN_TIME"));
		        	log.setLogOffTime(resultSet.getString("LOGOFF_TIME"));
		        	log.setComments(resultSet.getString("COMMENTS"));
		            return log;
		        });
		return loginDtls;
	}

	public String save(LoginDetails login) {
		Object[] params= {
				login.getId(),
				login.getName(), 
				login.getActualShift(),
				new Date(),
				login.getLoginTime(),
				login.getLogOffTime(), 
				login.getComments()
				};
		int count = this.jdbcTemplate.update(DBQueries.INSERT_LOG_DTLS, params);
		return count>0?"Login Details saved successfully with ":"Not inserted";

	}

	public LoginDetails findById(Integer id) {

		LoginDetails loginDtls = jdbcTemplate.queryForObject(
		        DBQueries.SELECT_LOG_DTLS_BY_ID,
		        (resultSet, rowNum) -> {
		        	LoginDetails log = new LoginDetails();
		        	log.setId(resultSet.getInt("ID"));
		        	log.setName(resultSet.getString("NAME"));
		        	log.setLoginDate(resultSet.getDate("LOGIN_DTE"));
		        	log.setActualShift(resultSet.getString("ACTUAL_SHIFT"));
		        	log.setLoginTime(resultSet.getString("LOGIN_TIME"));
		        	log.setLogOffTime(resultSet.getString("LOGOFF_TIME"));
		        	log.setComments(resultSet.getString("COMMENTS"));
		            return log;
		        },
		        new Object[] {id});
		return loginDtls;
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
