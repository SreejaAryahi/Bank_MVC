package com.digit.javaTraining.MVC.Controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.digit.javaTraining.MVC.Model.loanApp;

@WebServlet("/loan")

public class loanC extends HttpServlet{
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		int option=Integer.parseInt(req.getParameter("option"));
		
		loanApp loanapp=new loanApp();
		
		boolean x=loanapp.getLoan(option);
		
		HttpSession session = req.getSession();
		
		if(x==true) {
			session.setAttribute("l_id",loanapp.getL_id());
			session.setAttribute("l_type",loanapp.getL_type());
			session.setAttribute("tenure",loanapp.getTenure());
			session.setAttribute("interest",loanapp.getInterest());
			session.setAttribute("description",loanapp.getDescription());
			
			resp.sendRedirect("LoanDetails.jsp");


			

		}
		else {
			resp.sendRedirect("LoanFail.html");

		}
		
	}
	
}
