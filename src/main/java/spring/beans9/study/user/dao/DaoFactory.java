//package spring.beans9.study.user.dao;
//
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//
//@Configuration
//public class DaoFactory {
//	@Bean
//	public UserDao userDao() {
//		System.out.println("userDao");
//		UserDao userDao = new UserDao(connectionMaker());
//		return userDao;
//	}
//	
//	@Bean
//	public ConnectionMaker connectionMaker() {
//		return new DConnectionMaker();
//	}
//}
