package com.digit.javaTraining.MVC.Controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.digit.javaTraining.MVC.Model.BankApp;

@WebServlet("/Login")

public class LoginController  extends HttpServlet{
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		try {
			BankApp b = new BankApp();
			
			HttpSession session = req.getSession();
			
			b.setCust_id(Integer.parseInt(req.getParameter("cust_id")));
			b.setPin(Integer.parseInt(req.getParameter("pin")));
			
			boolean result1   = b.login();
			if(result1 == true) {
				session.setAttribute("customer_name",b.getCustomer_name() );
				session.setAttribute("accno",b.getAccno() );
				resp.sendRedirect("HomePage.jsp");
			}else {
				resp.sendRedirect("LoginFail.html");
			}
		}catch(Exception e) {
			
		}

	}
}
