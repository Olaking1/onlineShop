package cn.amy.shop.product.service;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import cn.amy.shop.product.dao.ProductDao;
import cn.amy.shop.product.vo.Product;
import cn.amy.shop.utils.PageBean;

@Transactional
public class ProductService {
	private ProductDao productDao;

	public void setProductDao(ProductDao productDao) {
		this.productDao = productDao;
	}

	public List<Product> findHot() {
		// TODO Auto-generated method stub
		return productDao.findHot();
	}

	public List<Product> findNew() {
		// TODO Auto-generated method stub
		return productDao.findNew();
	}

	public Product findByPid(Integer pid) {
		// TODO Auto-generated method stub
		return productDao.findByPid(pid);
	}

	//根据一级分类cid查询有分页的商品
	public PageBean<Product> findByPageCid(Integer cid, int page) {
		// TODO Auto-generated method stub
		PageBean<Product> pageBean = new PageBean<Product>();
		pageBean.setPage(page);
		int limit = 12;
		pageBean.setLimit(limit);
		//总记录数
		int totalCount = 0;
		totalCount = productDao.findCountByCid(cid);
		pageBean.setTotalCount(totalCount);
		
		//总页数
		int totalPage = 0;
		if(totalCount % limit == 0)
			totalPage = totalCount / limit;
		else 
			totalPage = totalCount / limit + 1;
		pageBean.setTotalPage(totalPage);
		
		//从哪开始
		int begin = (page - 1) * limit;
		List<Product> list = productDao.findByPageCid(cid , begin , limit);
		pageBean.setList(list);
		
		return pageBean;
	}

	public PageBean<Product> findByPageCsid(int csid, int page) {
		// TODO Auto-generated method stub
		PageBean<Product> pageBean = new PageBean<Product>();
		pageBean.setPage(page);
		int limit = 12;
		pageBean.setLimit(limit);
		//总记录数
		int totalCount = 0;
		totalCount = productDao.findCountByCsid(csid);
		pageBean.setTotalCount(totalCount);
		
		//总页数
		int totalPage = 0;
		if(totalCount % limit == 0)
			totalPage = totalCount / limit;
		else 
			totalPage = totalCount / limit + 1;
		pageBean.setTotalPage(totalPage);
		
		//从哪开始
		int begin = (page - 1) * limit;
		List<Product> list = productDao.findByPageCsid(csid , begin , limit);
		pageBean.setList(list);
		
		return pageBean;
	}
}
