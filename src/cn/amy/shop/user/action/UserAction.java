package cn.amy.shop.user.action;

import java.io.IOException;

import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;

import cn.amy.shop.user.service.UserService;
import cn.amy.shop.user.vo.User;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

public class UserAction extends ActionSupport implements ModelDriven<User> {
	private User user = new User();
	private UserService userService;
	private String checkcode;

	public void setCheckcode(String checkcode) {
		this.checkcode = checkcode;
	}

	public void setUserService(UserService userService) {
		this.userService = userService;
	}

	public String registPage() {
		return "registPage";
	}

	/*
	 * AJAX异步校验用户名的执行方法
	 * 
	 * @Throws IOException
	 */
	public String findByName() throws IOException {
		User existUser = userService.findByUsername(user.getName());
		// 获得response对象向页面输出
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setContentType("text/html;charset=UTF-8");
		if (existUser != null) {
			// 用户名已经存在
			response.getWriter().println("<font color='red'>用户名已经存在</font>");
		} else {
			// 用户名可以使用
			response.getWriter().println("<font color='green'>用户名可以使用</font>");
		}
		return NONE;
	}

	@Override
	public User getModel() {
		// TODO Auto-generated method stub
		return user;
	}

	public String regist() {
		String checkcode1 = (String) ServletActionContext.getRequest()
				.getSession().getAttribute("checkcode");
		if (checkcode1.equalsIgnoreCase(checkcode)) {
			userService.save(user);
			this.addActionMessage("注册成功，请前往邮箱激活！");
		}
		else{
			this.addActionError("验证码输入错误，请重新输入！");
			return "checkcodeFail";
		}
		return "msg";
	}

	public String active() {
		User existUser = userService.findByCode(user.getCode());
		if (existUser == null) {
			// 激活码错误
			this.addActionMessage("对不起，激活码错误，激活失败！");
		} else {
			// 激活成功
			// 修改用户状态
			existUser.setState(1);
			existUser.setCode(null);
			userService.update(existUser);
			this.addActionMessage("激活成功！请前往登录");
		}
		return "msg";
	}

	public String loginPage() {
		return "loginPage";
	}

	public String login() {
		User existUser = userService.login(user);
		if (existUser == null) {
			// 激活码错误
			this.addActionError("登录失败，用户名或密码错误或用户未激活");
			return LOGIN;
		} else {
			// 激活成功
			// 修改用户状态
			ServletActionContext.getRequest().getSession()
					.setAttribute("existUser", existUser);
			return "loginSuccess";
		}
	}

	public String quit() {
		ServletActionContext.getRequest().getSession().invalidate();
		return "quit";
	}
}
