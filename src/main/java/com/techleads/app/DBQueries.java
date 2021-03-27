package com.techleads.app;

public interface DBQueries {

	String INSERT_LOG_DTLS="INSERT INTO LOG_DTLS (ID ,NAME ,ACTUAL_SHIFT ,LOGIN_DTE ,LOGIN_TIME ,LOGOFF_TIME ,COMMENTS ) VALUES(?,?,?,?,?,?,?)";
	
	String SELECT_ALL_LOG_DTLS="SELECT ID ,NAME ,ACTUAL_SHIFT ,LOGIN_DTE ,LOGIN_TIME ,LOGOFF_TIME ,COMMENTS FROM LOG_DTLS";
	String SELECT_LOG_DTLS_BY_ID="SELECT ID ,NAME ,ACTUAL_SHIFT ,LOGIN_DTE ,LOGIN_TIME ,LOGOFF_TIME ,COMMENTS FROM LOG_DTLS WHERE ID=?";
}