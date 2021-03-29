package com.techleads.app.dao;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
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

	public List<LoginDetails> findAllById(Integer id) {
		List<LoginDetails> loginDtls = new ArrayList<>();
		try {
			Object[] params= {id};
			loginDtls = this.jdbcTemplate.query(DBQueries.SELECT_LOG_DTLS_BY_EMP_ID,  (resultSet, rowNum) -> {
				LoginDetails log = new LoginDetails();
				log.setId(resultSet.getInt("ID"));
				log.setActualShift(resultSet.getString("ACTUAL_SHIFT"));
				log.setLoginDate(resultSet.getDate("LOGIN_DATE", Calendar.getInstance()));
			  //log.setLoginDate(resultSet.getTimestamp("LOGIN_DATE"));
				log.setLoginTime(resultSet.getString("LOGIN_TIME"));
				log.setLogOffTime(resultSet.getString("LOGOFF_TIME"));
				log.setComments(resultSet.getString("COMMENTS"));
				log.setCreatedDte(resultSet.getTimestamp("CREATED_DTE"));
				log.setLastUpdatedDte(resultSet.getTimestamp("LAST_UPDATED_DTE"));
				log.setEmpId(resultSet.getInt("EMP_ID"));
				return log;
			},params);
			
			if(loginDtls.size()==0) {
				loginDtls.add(new LoginDetails());
			}
		} catch (DataAccessException e) {
			e.printStackTrace();
		}
		
		return loginDtls;
	}
	
	
	
	public List<LoginDetails> findAll() {
		Object[] params= {};
		List<LoginDetails> loginDtls = this.jdbcTemplate.query(DBQueries.SELECT_ALL_LOG_DTLS,  (resultSet, rowNum) -> {
			LoginDetails log = new LoginDetails();
			log.setId(resultSet.getInt("ID"));
			log.setActualShift(resultSet.getString("ACTUAL_SHIFT"));
			log.setLoginDate(resultSet.getTimestamp("LOGIN_DATE"));
			log.setLoginTime(resultSet.getString("LOGIN_TIME"));
			log.setLogOffTime(resultSet.getString("LOGOFF_TIME"));
			log.setComments(resultSet.getString("COMMENTS"));
			log.setCreatedDte(resultSet.getTimestamp("CREATED_DTE"));
			log.setLastUpdatedDte(resultSet.getTimestamp("LAST_UPDATED_DTE"));
			log.setEmpId(resultSet.getInt("EMP_ID"));
			return log;
		},params);
		
		if(loginDtls.size()==0) {
			loginDtls.add(new LoginDetails());
		}
		
		return loginDtls;
	}

	public String save(LoginDetails login) {
		Object[] params = { 
				login.getActualShift(),
				new Date(),
				login.getLoginTime(),
				login.getLogOffTime(), 
				login.getComments(),
				new Timestamp(new Date().getTime()),
				new Timestamp(new Date().getTime()),
				login.getEmpId()
				};
		int count = this.jdbcTemplate.update(DBQueries.INSERT_LOG_DTLS, params);
		return count > 0 ? "Login Details saved successfully with " : "Not inserted";

	}

	public LoginDetails findById(Integer id) {

		SqlRowSet rowSet = jdbcTemplate.queryForRowSet(DBQueries.SELECT_LOG_DTLS_BY_EMP_ID, new Object[] { id });

		LoginDetails logDtls = new LoginDetails();
		while (rowSet.next()) {
			logDtls.setId(rowSet.getInt("ID"));
			logDtls.setLoginDate(rowSet.getTimestamp("LOGIN_DATE"));
			logDtls.setActualShift(rowSet.getString("ACTUAL_SHIFT"));
			logDtls.setLoginTime(rowSet.getString("LOGIN_TIME"));
			logDtls.setLogOffTime(rowSet.getString("LOGOFF_TIME"));
			logDtls.setComments(rowSet.getString("COMMENTS"));
			logDtls.setEmpId(rowSet.getInt("EMP_ID"));
		}

		return logDtls;
	}

	public String updateById(LoginDetails login) {
		Object[] params = { 
				login.getActualShift(),
				login.getLoginTime(),
				login.getLogOffTime(), 
				login.getComments(),
				new Timestamp(new Date().getTime()),
				login.getId(),
				login.getEmpId()
				};
		
		
		int count = 0;
		try {
			count = jdbcTemplate.update(DBQueries.UPD_LOG_DTLS_BY_ID, params);
		} catch (DataAccessException e) {
			e.printStackTrace();
		}
		return count > 0 ? ("Updated record with id " + login.getId()) : " Not updated ";
	}

	public String deleteById(Integer id) {
		Object[] params = { id };
		int count = this.jdbcTemplate.update(DBQueries.DEL_LOG_DTLS_BY_ID, params);
		return count > 0 ? ("Deleted record with id " + id) : " Not Deleted ";

	}

}
