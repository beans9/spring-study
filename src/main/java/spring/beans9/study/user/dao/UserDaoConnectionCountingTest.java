//package spring.beans9.study.user.dao;
//
//import java.sql.SQLException;
//
//import org.springframework.context.annotation.AnnotationConfigApplicationContext;
//
//import spring.beans9.study.user.domain.User;
//
//public class UserDaoConnectionCountingTest {
//
//	public static void main(String[] args) throws ClassNotFoundException, SQLException {
//		// TODO Auto-generated method stub
//		AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext(CountingDaoFactory.class);
//		UserDao dao = ctx.getBean("userDao", UserDao.class);
//		
//		User user = new User();
//		user.setId("whiteship1");
//		user.setName("백기선");
//		user.setPassword("married");
//
//		dao.add(user);
//		
//		System.out.println(user.getId() + "등록성공");
//
//		User user2 = dao.get(user.getId());
//		System.out.println(user2.getName());
//
//		System.out.println("조회성공");
//
//		CountingConnectionMaker ccm = ctx.getBean("connectionMaker",CountingConnectionMaker.class);
//		System.out.println("connection count:" + ccm.getCount());
//	}
//
//}
