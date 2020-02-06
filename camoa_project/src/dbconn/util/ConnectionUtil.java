package dbconn.util;

import java.io.PrintWriter;
import java.sql.*;

public class ConnectionUtil {

	@SuppressWarnings("finally")
	public static Connection getConnection(String dns) {
		Connection conn = null;
		try {
			if (dns.equals("oracle")) {

				Class.forName("oracle.jdbc.driver.OracleDriver");
				DriverManager.setLogWriter(new PrintWriter(System.out));

				conn = DriverManager.getConnection
						("jdbc:oracle:thin:@localhost:1521:xe", "ragi", "q1w2e3r4");
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			return conn;
		}
	}
}
