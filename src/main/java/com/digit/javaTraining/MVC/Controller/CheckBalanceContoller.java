package com.digit.javaTraining.MVC.Controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
//import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSession;

import com.digit.javaTraining.MVC.Model.BankApp;

@WebServlet("/CheckBalance")

public class CheckBalanceContoller extends HttpServlet {
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		try {
			
			BankApp b = new BankApp();
			
			HttpSession session = req.getSession();
			
			//HttpSession session = req.getSession();
			
			
			session.getAttribute("customer_name");
			b.setAccno((int)session.getAttribute("accno"));
			boolean result2   = b.CheckBalance();
			if(result2 == true) {
				session.setAttribute("balance",b.getBalance() );
				resp.sendRedirect("Balance.jsp");
			}else {
				resp.sendRedirect("BalanceFail.jsp");
			}
		}catch(Exception e) {
			
		}

	}

}
