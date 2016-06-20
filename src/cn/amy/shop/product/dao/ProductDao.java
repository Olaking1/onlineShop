package cn.amy.shop.product.dao;

import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import cn.amy.shop.product.vo.Product;
import cn.amy.shop.utils.PageHibernateCallback;

public class ProductDao extends HibernateDaoSupport{

	public List<Product> findHot() {
		// TODO Auto-generated method stub
		DetachedCriteria criteria = DetachedCriteria.forClass(Product.class);
		//查询热门商品，条件是is_hot为1
		criteria.add(Restrictions.eq("is_hot", 1));
		//按倒序排列
		criteria.addOrder(Order.desc("pdate"));
		List<Product> list = this.getHibernateTemplate().findByCriteria(criteria,0,10);

		return list;
	}

	public List<Product> findNew() {
		// TODO Auto-generated method stub
		//离线条件
		DetachedCriteria criteria = DetachedCriteria.forClass(Product.class);
		//按倒序排列
		criteria.addOrder(Order.desc("pdate"));
		//执行查询
		List<Product> list = this.getHibernateTemplate().findByCriteria(criteria,0,10);

		return list;
	}

	public Product findByPid(Integer pid) {
		// TODO Auto-generated method stub
		return this.getHibernateTemplate().get(Product.class, pid);
	}

	public int findCountByCid(Integer cid) {
		// TODO Auto-generated method stub
		String hql = "select count(*) from Product p where p.categorySecond.category.cid = ?";
		List<Long> list = this.getHibernateTemplate().find(hql,cid);
		if(list != null && list.size() > 0){
			return list.get(0).intValue();
		}
		return 0;
	}

	public List<Product> findByPageCid(Integer cid, int begin, int limit) {
		// TODO Auto-generated method stub
		String hql = "select p from Product p join p.categorySecond cs join cs.category c where c.id = ?";
		List<Product> list = this.getHibernateTemplate().execute(new PageHibernateCallback<Product>(hql, new Object[]{cid}, begin, limit));
		if(list != null && list.size() > 0){
			return list;
		}
		return null;
	}

	public int findCountByCsid(int csid) {
		// TODO Auto-generated method stub
		String hql="select count(*) from Product p where p.categorySecond.csid = ?";
		List<Long> list = this.getHibernateTemplate().find(hql, csid);
		if(list != null && list.size() > 0){
			return list.get(0).intValue();
		}
		return 0;

	}

	public List<Product> findByPageCsid(int csid, int begin, int limit) {
		// TODO Auto-generated method stub
		String hql = "select p from Product p join p.categorySecond cs where cs.csid = ?";
		List<Product> list = this.getHibernateTemplate().execute(new PageHibernateCallback<Product>(hql, new Object[]{csid}, begin, limit));
		if(list != null && list.size() > 0){
			return list;
		}
		return null;
	}

}
