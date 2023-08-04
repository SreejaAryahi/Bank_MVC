package com.digit.javaTraining.MVC.Controller;

import java.io.IOException;
import java.sql.PreparedStatement;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.digit.javaTraining.MVC.Model.BankApp;

public class RegisterController {
	private PreparedStatement pstmt;

	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		try {
			BankApp b = new BankApp();
			b.setBankId(Integer.parseInt(req.getParameter("bank_id")));

			b.setBank_name(req.getParameter("bank_name"));

			b.setIfsc_code(req.getParameter("ifsc_code"));
			//retreiving accno
			b.setAccno(Integer.parseInt(req.getParameter("accno")));

			b.setPin(Integer.parseInt(req.getParameter("pin")));

			b.setCust_id(Integer.parseInt(req.getParameter("cust_id")));

			b.setCustomer_name(req.getParameter("cust_name"));

			b.setBalance(Integer.parseInt(req.getParameter("balance")));

			b.setEmail(req.getParameter("email"));

			b.setPhone(Long.parseLong(req.getParameter("phone")));
			boolean result = b.register();
			if(result==true) {
				resp.sendRedirect("RegisterSuccess.html");

			}else {
				resp.sendRedirect("RegisterFail.html");
			}



		}catch(Exception e) {
			e.printStackTrace();
		}


	}

}