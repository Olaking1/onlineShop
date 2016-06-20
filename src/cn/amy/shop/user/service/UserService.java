package cn.amy.shop.user.service;

import org.springframework.transaction.annotation.Transactional;

import cn.amy.shop.user.dao.UserDao;
import cn.amy.shop.user.vo.User;
import cn.amy.shop.utils.MailUitils;
import cn.amy.shop.utils.UUIDUtils;

/*
 * �û�ģ��ҵ������
 */
@Transactional
public class UserService {
	private UserDao userDao;

	public void setUserDao(UserDao userDao) {
		this.userDao = userDao;
	}
	
	public User findByUsername(String username){
		return userDao.findByUsername(username);
	}
	
	public void save(User user){
		user.setState(0);//0�����û�δ���1�����û��Ѽ���
		String code = UUIDUtils.getUUID()+UUIDUtils.getUUID();
		user.setCode(code);
		userDao.save(user);
		
		MailUitils.sendMail(user.getEmail(), code);
	}

	public User findByCode(String code) {
		// TODO Auto-generated method stub
		return userDao.findByCode(code);
	}

	public void update(User existUser) {
		// TODO Auto-generated method stub
		userDao.update(existUser);
	}

	public User login(User user) {
		// TODO Auto-generated method stub
		return userDao.login(user);
	}
}
