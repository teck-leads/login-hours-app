package com.techleads.app.dao;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Repository;

import com.techleads.app.DBQueries;
import com.techleads.app.model.Login;
import com.techleads.app.model.LoginDetails;
@Repository
public class LoginDao {
	
	private JdbcTemplate jdbcTemplate;

	@Autowired
	public LoginDao(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}
	
	
	public List<Login> findAll() {
		List<Login> logins = this.jdbcTemplate.query(DBQueries.SELECT_ALL_LOGINS, (resultSet, rowNum) -> {
			Login login = new Login();
			login.setId(resultSet.getInt("EMP_ID"));
			login.setName(resultSet.getString("NAME"));
			
			return login;
		});
		return logins;
	}
	
	public String save(Login login) {
		Object[] params = {login.getId(), login.getName() };
		int count = this.jdbcTemplate.update(DBQueries.INSERT_LOGINS, params);
		return count > 0 ? "Login Details saved successfully with " : "Not inserted";

	}
	
	public Login findById(Integer id) {

		SqlRowSet rowSet = jdbcTemplate.queryForRowSet(DBQueries.SELECT_LOGINS_BY_ID, new Object[] { id });

		Login login = new Login();
		while (rowSet.next()) {
			login.setId(rowSet.getInt("EMP_ID"));
			login.setName(rowSet.getString("NAME"));
			
		}

		return login;
	}
	
	public String updateById(Login login) {

		Object[] params = {login.getName(), login.getId()  };
		int count = jdbcTemplate.update(DBQueries.UPD_LOGIN_BY_ID, params);
		return count > 0 ? ("Updated record with id " + login.getId()) : " Not updated ";
	}
	
	public String deleteById(Integer id) {
		Object[] params = { id };
		int count = this.jdbcTemplate.update(DBQueries.DEL_LOGIN_BY_ID, params);
		return count > 0 ? ("Deleted record with id " + id) : " Not Deleted ";

	}

}
