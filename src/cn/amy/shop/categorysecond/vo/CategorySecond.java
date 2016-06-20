package cn.amy.shop.categorysecond.vo;

import java.util.HashSet;
import java.util.Set;

import cn.amy.shop.category.vo.Category;
import cn.amy.shop.product.vo.Product;

public class CategorySecond {
	private Integer csid;
	private String csname;
	//所属一级分类的对象
	private Category category;
	private Set<Product> products = new HashSet<Product>();

	public Set<Product> getProducts() {
		return products;
	}

	public void setProducts(Set<Product> products) {
		this.products = products;
	}

	public Integer getCsid() {
		return csid;
	}

	public String getCsname() {
		return csname;
	}

	public Category getCategory() {
		return category;
	}

	public void setCsid(Integer csid) {
		this.csid = csid;
	}

	public void setCsname(String cname) {
		this.csname = cname;
	}

	public void setCategory(Category category) {
		this.category = category;
	}
}
