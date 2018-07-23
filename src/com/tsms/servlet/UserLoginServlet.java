package com.tsms.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.tsms.dao.impl.UserDaoImpl;
import com.tsms.entity.User;
import com.tsms.util.EncryptUtil;

import net.sf.json.JSONObject;

/**
 * Servlet implementation class UserLoginServlet
 */
@WebServlet("/UserLoginServlet.json")
public class UserLoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UserLoginServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		response.setContentType("application/json;charset=UTF-8");
		
		
		String userName = request.getParameter("username");
		String password = request.getParameter("password");
		String captcha = request.getParameter("captcha");
		HttpSession session = request.getSession();
		Object validate =  session.getAttribute(com.google.code.kaptcha.Constants.KAPTCHA_SESSION_KEY);
		
		JSONObject json = new JSONObject();
		if (validate.toString().equalsIgnoreCase(captcha)) {
			User user = new UserDaoImpl().login(userName);
			if (user != null) {
				String pwd = EncryptUtil.encryptMD5(password + user.getSalt());
				if (pwd.equals(user.getPassword())) {
					session.setAttribute("username", userName);
					session.setAttribute("user", user);
					json.put("success", true);
					json.put("msg", "登录成功");
				} else {
					json.put("success", false);
					json.put("msg", "密码有误!");
				}
			} else {
				json.put("success", false);
				json.put("msg", "账号有误!");
			}
		} else {
			json.put("success", false);
			json.put("msg", "验证码有误!");
		}
		response.getWriter().append(json.toString());
		
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}