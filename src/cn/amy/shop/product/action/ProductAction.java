package cn.amy.shop.product.action;

import java.util.List;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

import cn.amy.shop.category.service.CategoryService;
import cn.amy.shop.product.service.ProductService;
import cn.amy.shop.product.vo.Product;
import cn.amy.shop.utils.PageBean;

public class ProductAction extends ActionSupport implements ModelDriven<Product>{

	private Product product = new Product();
	private ProductService productService;
	//接收cid，因为商品类中只有二级分类id，没有一级分类id，所以要自己创建
	private Integer cid;
	//注入一级分类的Service,因为categoryService中已经实现了一级分类的查询
	private CategoryService categoryservice;
	private int page;
	private Integer csid;

	public Integer getCsid() {
		return csid;
	}

	public void setCsid(Integer csid) {
		this.csid = csid;
	}

	public void setPage(int page) {
		this.page = page;
	}

	public void setCategoryservice(CategoryService categoryservice) {
		this.categoryservice = categoryservice;
	}

	public void setCid(Integer cid) {
		this.cid = cid;
	}

	public void setProductService(ProductService productService) {
		this.productService = productService;
	}
	
	public Integer getCid() {
		return cid;
	}

	@Override
	public String execute() throws Exception {
		// TODO Auto-generated method stub
		return super.execute();
	}

	@Override
	public Product getModel() {
		// TODO Auto-generated method stub
		return product;
	}
	
	public String findByPid(){
		product = productService.findByPid(product.getPid());
		return "findByPid";
	}
	
	public String findByCid(){
		//可以通过session获取，因为session中已经保存了一级分类的id
		//也可以通过categoryservice再查询一次
		//List<Category> cList = categoryservice.findAll();
		PageBean<Product> pageBean = productService.findByPageCid(cid,page);//根据一级分类查询商品，带分页
		ActionContext.getContext().getValueStack().set("pageBean", pageBean);
		return "findByCid";
	}
	
	public String findByCsid(){
		PageBean<Product> pageBean = productService.findByPageCsid(csid,page);
		ActionContext.getContext().getValueStack().set("pageBean", pageBean);
		return "findByCsid";
	}
	
}
