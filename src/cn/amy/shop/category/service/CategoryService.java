package cn.amy.shop.category.service;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import cn.amy.shop.category.dao.CategoryDao;
import cn.amy.shop.category.vo.Category;

@Transactional
public class CategoryService {

	private CategoryDao categoryDao;
	
	public void setCategoryDao(CategoryDao categoryDao) {
		this.categoryDao = categoryDao;
	}

	public List<Category> findAll() {
		// TODO Auto-generated method stub
		return categoryDao.findAll();
	}

}
