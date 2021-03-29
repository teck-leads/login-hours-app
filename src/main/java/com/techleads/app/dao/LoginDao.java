package com.techleads.app.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.techleads.app.DBQueries;
import com.techleads.app.model.Login;
import com.techleads.app.model.LoginDetails;

@Repository
public class LoginDao {

	private JdbcTemplate jdbcTemplate;

	private LoginDetailsDAO loginDetailsDAO;

	@Autowired
	public LoginDao(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}
	
	@Autowired
	public void setLoginDetailsDAO(LoginDetailsDAO loginDetailsDAO) {
		this.loginDetailsDAO = loginDetailsDAO;
	}


	public List<Login> findAll() {
		List<Login> logins = this.jdbcTemplate.query(DBQueries.SELECT_ALL_LOGINS, (resultSet, rowNum) -> {
			Login login = new Login();
			login.setId(resultSet.getInt("EMP_ID"));
			login.setName(resultSet.getString("NAME"));
			login.setCreatedDte(resultSet.getTimestamp("CREATED_DTE"));
			login.setLastUpdatedDte(resultSet.getTimestamp("LAST_UPDATED_DTE"));

			return login;
		});
		return logins;
	}

	


	public String save(Login login) {
		Object[] params = { login.getId(),
				login.getName(), 
				new Timestamp(new Date().getTime()),
				new Timestamp(new Date().getTime()) };
		int count = this.jdbcTemplate.update(DBQueries.INSERT_LOGINS, params);
		return count > 0 ? "Login Details saved successfully with " : "Not inserted";

	}

	public Login findById(Integer id) {
		Login login = new Login();
		Object[] params= {id};
		
		/* timestamp is not working
		SqlRowSet rowSet = jdbcTemplate.queryForRowSet(DBQueries.SELECT_LOGINS_BY_ID, new Object[] { id });

		
		while (rowSet.next()) {
			login.setId(rowSet.getInt("EMP_ID"));
			login.setName(rowSet.getString("NAME"));
			List<LoginDetails> loginDtls = loginDetailsDAO.findAllById(id);
			
			login.setLastUpdatedDte(rowSet.getTimestamp("LAST_UPDATED"));
			login.setLogDetails(loginDtls);

		}
		*/
		/*
		List<Map<String, Object>> queryForList = jdbcTemplate.queryForList(DBQueries.SELECT_LOGINS_BY_ID, new Object[] { id });
		Map<String, Object> queryForMap = jdbcTemplate.queryForMap(DBQueries.SELECT_LOGINS_BY_ID, new Object[] { id });
		queryForMap.forEach((k,v)->{System.out.println(k+" "+v);});
		*/
		
		/*
		login = jdbcTemplate.queryForObject(DBQueries.SELECT_LOGINS_BY_ID, new RowMapper<Login>() {

			@Override
			public Login mapRow(ResultSet rs, int rowNum) throws SQLException {
				Login login = new Login();
				login.setId(rs.getInt("EMP_ID"));
				login.setName(rs.getString("NAME"));
				login.setCreatedDte(rs.getTimestamp("CREATED_DTE"));
				login.setLastUpdatedDte(rs.getTimestamp("LAST_UPDATED_DTE"));
				List<LoginDetails> loginDtls = loginDetailsDAO.findAllById(id);
				login.setLogDetails(loginDtls);
				return login;
			}

			
		},id);
		*/
		
		 login = jdbcTemplate.query(DBQueries.SELECT_LOGINS_BY_ID, new ResultSetExtractor<Login>() {

			@Override
			public Login extractData(ResultSet rs) throws SQLException, DataAccessException {
				Login login = new Login();
				while(rs.next()) {
					login.setId(rs.getInt("EMP_ID"));
					login.setName(rs.getString("NAME"));
					login.setCreatedDte(rs.getTimestamp("CREATED_DTE"));
					login.setLastUpdatedDte(rs.getTimestamp("LAST_UPDATED_DTE"));
					List<LoginDetails> loginDtls = loginDetailsDAO.findAllById(id);
					login.setLogDetails(loginDtls);
					
				}
				return login;
			}
		}, params);
		
		
		return login;
	}

	public String updateById(Login login) {

		Object[] params = { login.getName(), login.getId(), new Timestamp(new Date().getTime()) };
		int count = jdbcTemplate.update(DBQueries.UPD_LOGIN_BY_ID, params);
		return count > 0 ? ("Updated record with id " + login.getId()) : " Not updated ";
	}

	public String deleteById(Integer id) {
		Object[] params = { id };
		int count = this.jdbcTemplate.update(DBQueries.DEL_LOGIN_BY_ID, params);
		return count > 0 ? ("Deleted record with id " + id) : " Not Deleted ";

	}

}
