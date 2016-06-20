package cn.amy.shop.index.action;

import java.util.List;

import cn.amy.shop.category.service.CategoryService;
import cn.amy.shop.category.vo.Category;
import cn.amy.shop.product.service.ProductService;
import cn.amy.shop.product.vo.Product;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
/**
 * 首页访问的action
 * @author lzy
 *
 */

public class IndexAction extends ActionSupport{
	
	private CategoryService categoryService;
	private ProductService productService;
	
	public void setProductService(ProductService productService) {
		this.productService = productService;
	}

	/*
	 * 执行首页访问的方法
	 * @see com.opensymphony.xwork2.ActionSupport#execute()
	 */
	public String execute(){
		List<Category> cList =  categoryService.findAll();
		ActionContext.getContext().getSession().put("cList", cList);
		//查询热门商品
		List<Product> hList = productService.findHot();
		//保存到值栈中
		ActionContext.getContext().getValueStack().set("hList", hList);
		//查询最新商品
		List<Product> nList = productService.findNew();
		ActionContext.getContext().getValueStack().set("nList", hList);
		return "index";
	}

	public void setCategoryService(CategoryService categoryService) {
		this.categoryService = categoryService;
	}
}
