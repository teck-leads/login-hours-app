package com.techleads.app.dao;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
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

	public List<LoginDetails> findAll() {
		List<LoginDetails> loginDtls = this.jdbcTemplate.query(DBQueries.SELECT_ALL_LOG_DTLS, (resultSet, rowNum) -> {
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
		Object[] params = { login.getId(), login.getName(), login.getActualShift(), new Date(), login.getLoginTime(),
				login.getLogOffTime(), login.getComments() };
		int count = this.jdbcTemplate.update(DBQueries.INSERT_LOG_DTLS, params);
		return count > 0 ? "Login Details saved successfully with " : "Not inserted";

	}

	public LoginDetails findById(Integer id) {

		SqlRowSet rowSet = jdbcTemplate.queryForRowSet(DBQueries.SELECT_LOG_DTLS_BY_ID, new Object[] { id });

		LoginDetails logDtls = new LoginDetails();
		while (rowSet.next()) {
			logDtls.setId(rowSet.getInt("ID"));
			logDtls.setName(rowSet.getString("NAME"));
			logDtls.setLoginDate(rowSet.getDate("LOGIN_DTE"));
			logDtls.setActualShift(rowSet.getString("ACTUAL_SHIFT"));
			logDtls.setLoginTime(rowSet.getString("LOGIN_TIME"));
			logDtls.setLogOffTime(rowSet.getString("LOGOFF_TIME"));
			logDtls.setComments(rowSet.getString("COMMENTS"));
		}

		return logDtls;
	}

	public String updateById(LoginDetails logDtls) {

		Object[] params = { logDtls.getActualShift(), logDtls.getLoginTime(), logDtls.getLogOffTime(),
				logDtls.getComments(), logDtls.getId() };
		int count = jdbcTemplate.update(DBQueries.UPD_LOG_DTLS_BY_ID, params);
		return count > 0 ? ("Updated record with id " + logDtls.getId()) : " Not updated ";
	}

	public String deleteById(Integer id) {
		Object[] params = { id };
		int count = this.jdbcTemplate.update(DBQueries.DEL_LOG_DTLS_BY_ID, params);
		return count > 0 ? ("Deleted record with id " + id) : " Not Deleted ";

	}

}
