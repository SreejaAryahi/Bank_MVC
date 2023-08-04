package com.digit.javaTraining.MVC.Model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class transferstatus {
	
	int cust_id;
	String bank_name;
	String ifsc_code;
	int sender_accno;
	String receiver_ifsc;
	int receiver_accno;
	String url = "jdbc:mysql://localhost:3306/projectbank";
	String user = "root";
	String pwd = "Sreejarac@18";
	

	
	private Connection con;
	private PreparedStatement pstmt;
	private ResultSet res1;
	private ResultSet res2;
	private ResultSet res3;
	/**
	 * @return the cust_id
	 */
	public int getCust_id() {
		return cust_id;
	}
	/**
	 * @param cust_id the cust_id to set
	 */
	public void setCust_id(int cust_id) {
		this.cust_id = cust_id;
	}
	/**
	 * @return the bank_name
	 */
	public String getBank_name() {
		return bank_name;
	}
	/**
	 * @param bank_name the bank_name to set
	 */
	public void setBank_name(String bank_name) {
		this.bank_name = bank_name;
	}
	/**
	 * @return the ifsc_code
	 */
	public String getIfsc_code() {
		return ifsc_code;
	}
	/**
	 * @param ifsc_code the ifsc_code to set
	 */
	public void setIfsc_code(String ifsc_code) {
		this.ifsc_code = ifsc_code;
	}
	/**
	 * @return the sender_accno
	 */
	public int getSender_accno() {
		return sender_accno;
	}
	/**
	 * @param sender_accno the sender_accno to set
	 */
	public void setSender_accno(int sender_accno) {
		this.sender_accno = sender_accno;
	}
	/**
	 * @return the receiver_ifsc
	 */
	public String getReceiver_ifsc() {
		return receiver_ifsc;
	}
	/**
	 * @param receiver_ifsc the receiver_ifsc to set
	 */
	public void setReceiver_ifsc(String receiver_ifsc) {
		this.receiver_ifsc = receiver_ifsc;
	}
	/**
	 * @return the receiver_accno
	 */
	public int getReceiver_accno() {
		return receiver_accno;
	}
	/**
	 * @param receiver_accno the receiver_accno to set
	 */
	public void setReceiver_accno(int receiver_accno) {
		this.receiver_accno = receiver_accno;
	}
	/**
	 * @return the amount
	 */
	public float getAmount() {
		return amount;
	}
	/**
	 * @param amount the amount to set
	 */
	public void setAmount(float amount) {
		this.amount = amount;
	}
	float amount;
	
	public boolean transferAmount(int pin) {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			con = DriverManager.getConnection(url, user, pwd);
			pstmt = con.prepareStatement("select * from bankapp where cust_id=? and ifsc_code=? and accno=? and pin=?");

			pstmt.setInt(1, cust_id);
			pstmt.setString(2, ifsc_code);
			pstmt.setInt(3, sender_accno);
			pstmt.setInt(4, pin);

			res1 = pstmt.executeQuery();

			if (res1.next() == true) {
				pstmt = con.prepareStatement("select * from bankapp where ifsc_code=? and accno=?");
				pstmt.setString(1, receiver_ifsc);
				pstmt.setInt(2, receiver_accno);
				res2 = pstmt.executeQuery();

				if (res2.next() == true) {
					pstmt = con.prepareStatement("select balance from bankapp where accno=?");
					pstmt.setInt(1, sender_accno);
					res3 = pstmt.executeQuery();
					res3.next();
					int balance = res3.getInt(1);

					if (balance > amount) {
						pstmt = con.prepareStatement("update bankapp set balance=balance-? where accno=?");
						pstmt.setFloat(1, amount);
						pstmt.setInt(2, sender_accno);

						int x1 = pstmt.executeUpdate();

						if (x1 > 0) {
							pstmt = con.prepareStatement("update bankapp set balance=balance+? where accno=?");
							pstmt.setFloat(1, amount);
							pstmt.setInt(2, receiver_accno);

							int x2 = pstmt.executeUpdate();

							if (x2 > 0) {
								pstmt = con.prepareStatement("insert into transferStatus values(?,?,?,?,?,?,?)");
								pstmt.setInt(1, cust_id);
								pstmt.setString(2, bank_name);
								pstmt.setString(3, ifsc_code);
								pstmt.setInt(4, sender_accno);
								pstmt.setString(5, receiver_ifsc);
								pstmt.setInt(6, receiver_accno);
								pstmt.setFloat(7, amount);

								int x3 = pstmt.executeUpdate();
								if (x3 > 0) {
									return true;

								} else {
									//String transferdetails= "TransactionDetailsError";
									//session.setAttribute("error",transferdetails);
									//resp.sendRedirect("TransferFail.jsp");
									return false;

								}

							} else {
								//String transferdetails= "BalanceCreditError";
								//session.setAttribute("error",transferdetails);
								//resp.sendRedirect("TransferFail.jsp");
								return false;

							}
						} else {
							//String transferdetails= "BalanceDebitError";
							//session.setAttribute("error",transferdetails);
							//resp.sendRedirect("TransferFail.jsp");
							return false;
						}

					} else {
						//String transferdetails= "InsufficientBalanceError";
						//session.setAttribute("error",transferdetails);
						//resp.sendRedirect("TransferFail.jsp");
						return false;
					}

				} else {
					//String transferdetails= "RecieverCredentialsError";
					//session.setAttribute("error",transferdetails);
					//resp.sendRedirect("TransferFail.jsp");
					return false;
				}

			} else {
				//String transferdetails= "SenderCredentialsError";
				//session.setAttribute("error",transferdetails);
				//resp.sendRedirect("TransferFail.jsp");
				return false;
			}

		}catch(Exception e) {
			e.printStackTrace();
		}
		return false;
	}

}
