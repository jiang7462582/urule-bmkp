package com.bmkp.urule.demo;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.SessionFactory;

import com.bstek.urule.repo.EnvironmentProvider;
import com.bstek.urule.repo.SysUser;
import com.bstek.urule.repo.User;

/**
 * @author Jacky.gao
 * @since 2014年12月23日
 */
public class DefaultEnvironmentProvider implements EnvironmentProvider{
	private SessionFactory sessionFactory;
	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}
	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}
	public SysUser getLoginUser() {
		User user=new User();
		user.setUsername("Guest");
		return user;
	}
	
	public List<SysUser> getUsers() {
		List<SysUser> users=new ArrayList<SysUser>();
		User user=new User();
		user.setUsername("Guest");
		users.add(user);
		user=new User();
		user.setUsername("SuperMan");
		users.add(user);
		user=new User();
		user.setUsername("Tom");
		users.add(user);
		return users;
	}
	
}
