package com.digit.javaTraining.MVC.Model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class loanApp {
	int l_id;
	String l_type;
	int tenure;
	float interest;
	String description;
	private Connection con;
	private PreparedStatement pstmt;
	private ResultSet res;
	/**
	 * @return the l_id
	 */
	public int getL_id() {
		return l_id;
	}
	/**
	 * @param l_id the l_id to set
	 */
	public void setL_id(int l_id) {
		this.l_id = l_id;
	}
	/**
	 * @return the l_type
	 */
	public String getL_type() {
		return l_type;
	}
	/**
	 * @param l_type the l_type to set
	 */
	public void setL_type(String l_type) {
		this.l_type = l_type;
	}
	/**
	 * @return the tenure
	 */
	public int getTenure() {
		return tenure;
	}
	/**
	 * @param tenure the tenure to set
	 */
	public void setTenure(int tenure) {
		this.tenure = tenure;
	}
	/**
	 * @return the interest
	 */
	public float getInterest() {
		return interest;
	}
	/**
	 * @param interest the interest to set
	 */
	public void setInterest(float interest) {
		this.interest = interest;
	}
	/**
	 * @return the description
	 */
	public String getDescription() {
		return description;
	}
	/**
	 * @param description the description to set
	 */
	public void setDescription(String description) {
		this.description = description;
	}
	/**
	 * @return the con
	 */
	public Connection getCon() {
		return con;
	}
	/**
	 * @param con the con to set
	 */
	public void setCon(Connection con) {
		this.con = con;
	}
	/**
	 * @return the pstmt
	 */
	public PreparedStatement getPstmt() {
		return pstmt;
	}
	/**
	 * @param pstmt the pstmt to set
	 */
	public void setPstmt(PreparedStatement pstmt) {
		this.pstmt = pstmt;
	}
	/**
	 * @return the res
	 */
	public ResultSet getRes() {
		return res;
	}
	/**
	 * @param res the res to set
	 */
	public void setRes(ResultSet res) {
		this.res = res;
	}
	
	public loanApp() {
		String url = "jdbc:mysql://localhost:3306/projectbank";// bankingapplication
		String user = "root";
		String pwd = "Sreejarac@18";

		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			System.out.println("Driver loaded");

			con = DriverManager.getConnection(url, user, pwd);
			System.out.println("connection created");

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public boolean getLoan(int option) {
		try {
			

			String sql = "select * from loan where l_id=?";

			pstmt = con.prepareStatement(sql);

			
			
			pstmt.setInt(1, option);
			
			res = pstmt.executeQuery();

			if(res.next()==true) {
//				session.setAttribute("loan_id", res.getInt("loan_id"));
//				session.setAttribute("loan_type", res.getString("loan_type"));
//				session.setAttribute("tenure", res.getInt("tenure"));
//				session.setAttribute("interest", res.getFloat("interest"));
//				session.setAttribute("loan_type", res.getString("description"));
				setL_id(res.getInt("l_id"));
				setL_type(res.getString("l_type"));
				setTenure(res.getInt("tenure"));
				setInterest(res.getFloat("interest"));
				setDescription(res.getString("description"));
				return true;
				
//				resp.sendRedirect("/BankingApplication/loanDetails.jsp");

			} 
			else {
//				resp.sendRedirect("/BankingApplication/loanDetailsFail.html");
				return false;
			}
			
		}
		catch(Exception e) {
			e.printStackTrace();
		}

		
		
		return false;
	}

}
