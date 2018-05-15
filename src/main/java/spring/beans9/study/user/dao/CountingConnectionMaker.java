package spring.beans9.study.user.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class CountingConnectionMaker implements ConnectionMaker {
	int count = 0;
	private ConnectionMaker realConnectionMaker;
	public CountingConnectionMaker(ConnectionMaker realConnectionMaker) {
		this.realConnectionMaker = realConnectionMaker;
	}
	
	public Connection makeConnection() throws SQLException {
		this.count++;
		return realConnectionMaker.makeConnection();
	}
	
	public int getCount() {
		return this.count;
	}

}
