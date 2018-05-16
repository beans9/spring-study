package spring.beans9.study.user.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class CountingConnectionMaker implements ConnectionMaker {
	int count = 0;
	private ConnectionMaker connectionMaker;
	
	public void setConnectionMaker(ConnectionMaker connectionMaker) {
		this.connectionMaker = connectionMaker;
	}

	public CountingConnectionMaker(ConnectionMaker realConnectionMaker) {
		this.connectionMaker = realConnectionMaker;
	}
	
	public Connection makeConnection() throws SQLException {
		this.count++;
		return connectionMaker.makeConnection();
	}
	
	public int getCount() {
		return this.count;
	}
	
	CountingConnectionMaker(){super();}
}
