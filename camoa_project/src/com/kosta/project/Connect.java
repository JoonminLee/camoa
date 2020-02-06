package com.kosta.project;

import java.sql.Connection;
import java.sql.SQLException;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

public class Connect {
	public static Connection getConnection() throws NamingException, SQLException {//jndi dbcp pool
		
		Context initCtx= new InitialContext();
	
		DataSource ds =(DataSource)initCtx.lookup("java:comp/env/jdbc:PBoardDB");
		
		return ds.getConnection();
	
}
}
