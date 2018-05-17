package spring.beans9.study.user.domain;

public class User {
	/*
	 create database spring;
	 use spring;

	 create table users (
	 id varchar(10) primary key,
	 name varchar(20) not null,
	 password varchar(10) not null);
	 */
	String id;
	String name;
	String password;
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
	public User(){}
	public User(String id, String name, String password){
		this.id = id;
		this.name = name;
		this.password = password;
	}
}

