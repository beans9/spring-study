package spring.beans9.study.user.service;

import java.util.List;

import spring.beans9.study.user.dao.Level;
import spring.beans9.study.user.dao.UserDao;
import spring.beans9.study.user.domain.User;

public class UserService {
	public static final int MIN_LOGCOUNT_FOR_SILVER = 50;
	public static final int MIN_RECOMMEND_FOR_GOLD = 30;
	UserDao userDao;

	public void setUserDao(UserDao userDao) {
		this.userDao = userDao;
	}
	
	public void upgradeLevels() {
		List<User> users = userDao.getAll();
		
		for(User user : users) {
			if (canUpgradeLevel(user)) {
				upgradeLevel(user); 
			}
		}
	}
	

	protected void upgradeLevel(User user) {
		user.upgradeLevel();
		userDao.update(user);
	}

	private boolean canUpgradeLevel(User user) {
		// TODO Auto-generated method stub
		Level currentLevel = user.getLevel();
		switch(currentLevel) {
			case BASIC: return (user.getLogin()>=MIN_LOGCOUNT_FOR_SILVER);
			case SILVER: return (user.getRecommend()>=MIN_RECOMMEND_FOR_GOLD);
			case GOLD: return false;
			default: throw new IllegalArgumentException("unknow level:" + currentLevel); 
		}
	}

	public void add(User user) {
		// TODO Auto-generated method stub
		if(user.getLevel() == null) {
			user.setLevel(Level.BASIC);
		}
		userDao.add(user);
	}
}
