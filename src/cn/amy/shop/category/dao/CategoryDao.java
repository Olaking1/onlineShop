package cn.amy.shop.category.dao;

import java.util.List;

import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import cn.amy.shop.category.vo.Category;

public class CategoryDao extends HibernateDaoSupport{

	public List<Category> findAll() {
		// TODO Auto-generated method stub
		String hql="from Category";
		List<Category> list = this.getHibernateTemplate().find(hql);
		return list;
	}
	
}
