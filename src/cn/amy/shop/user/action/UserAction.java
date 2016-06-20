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
	 * AJAX�첽У���û�����ִ�з���
	 * 
	 * @Throws IOException
	 */
	public String findByName() throws IOException {
		User existUser = userService.findByUsername(user.getName());
		// ���response������ҳ�����
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setContentType("text/html;charset=UTF-8");
		if (existUser != null) {
			// �û����Ѿ�����
			response.getWriter().println("<font color='red'>�û����Ѿ�����</font>");
		} else {
			// �û�������ʹ��
			response.getWriter().println("<font color='green'>�û�������ʹ��</font>");
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
			this.addActionMessage("ע��ɹ�����ǰ�����伤�");
		}
		else{
			this.addActionError("��֤������������������룡");
			return "checkcodeFail";
		}
		return "msg";
	}

	public String active() {
		User existUser = userService.findByCode(user.getCode());
		if (existUser == null) {
			// ���������
			this.addActionMessage("�Բ��𣬼�������󣬼���ʧ�ܣ�");
		} else {
			// ����ɹ�
			// �޸��û�״̬
			existUser.setState(1);
			existUser.setCode(null);
			userService.update(existUser);
			this.addActionMessage("����ɹ�����ǰ����¼");
		}
		return "msg";
	}

	public String loginPage() {
		return "loginPage";
	}

	public String login() {
		User existUser = userService.login(user);
		if (existUser == null) {
			// ���������
			this.addActionError("��¼ʧ�ܣ��û��������������û�δ����");
			return LOGIN;
		} else {
			// ����ɹ�
			// �޸��û�״̬
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
