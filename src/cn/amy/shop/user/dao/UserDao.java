package cn.amy.shop.user.dao;

import java.util.List;

import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import cn.amy.shop.user.vo.User;

/*
 * 用户模块持久化层
 */

public class UserDao extends HibernateDaoSupport{
	public User findByUsername(String username){
		String hql = "from User where username = ?";
		List<User> list = this.getHibernateTemplate().find(hql , username);
		if(list != null && list.size() > 0){
			return list.get(0);
		}
		
		return null;
	}
	
	//注册用户存入数据库
	public void save(User user){
		this.getHibernateTemplate().save(user);
	}

	public User findByCode(String code) {
		// TODO Auto-generated method stub
		String hql = "from User where code = ?";
		List<User> list = this.getHibernateTemplate().find(hql,code);
		
		if(list != null && list.size() > 0){
			return list.get(0);
		}
		return null;
	}

	public void update(User existUser) {
		// TODO Auto-generated method stub
		this.getHibernateTemplate().update(existUser);
	}

	public User login(User user) {
		// TODO Auto-generated method stub
		String hql = "from User where username = ? and password = ? and state = ?";
		List<User> list = this.getHibernateTemplate().find(hql,user.getUsername(),user.getPassword(),1);
		if(list != null && list.size() > 0){
			return list.get(0);
		}
		return null;
	}
}
