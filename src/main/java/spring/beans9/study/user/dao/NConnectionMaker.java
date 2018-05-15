package spring.beans9.study.user.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class NConnectionMaker implements ConnectionMaker {

	public Connection makeConnection() throws SQLException {
		Connection c = DriverManager.getConnection("jdbc:mysql://localhost/spring?puseSSL=false", "spring", "book");
		return c;
	}

}
