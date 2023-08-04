package com.digit.javaTraining.MVC.Controller;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.digit.javaTraining.MVC.Model.BankApp;

@WebServlet("/ChangePassword")

public class ChangePasswordController extends HttpServlet{
	@Override
	public void service(HttpServletRequest req,HttpServletResponse resp)  throws ServletException, IOException{
		BankApp bankapp=new BankApp();
		HttpSession session=req.getSession();
		int accno=(int)session.getAttribute("accno");
		bankapp.setPin(Integer.parseInt(req.getParameter("old_pwd")));
		int n_pwd=Integer.parseInt(req.getParameter("n_pwd"));
		int c_pwd=Integer.parseInt(req.getParameter("c_pwd"));
		if(c_pwd==n_pwd)
		{
			boolean b=bankapp.ChangePasswords(accno,n_pwd);
			if(b==true)
			{
				resp.sendRedirect("PasswordChangeSuccess.html");
			}
			else
			{
				resp.sendRedirect("PasswordChangeFail.html");
			}
		}
		
		
	}

}
