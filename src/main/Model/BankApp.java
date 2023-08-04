package com.digit.javaTraining.MVC.Model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class BankApp {
	int bank_id;
	String bank_name;
	String ifsc_code;
	int accno;
	int n_pwd;
	int old_pwd;
	int pin;
	int cust_id;
	int c_pwd;
	String customer_name;
	int balance;
	String email;
	long phone;
	private PreparedStatement pstmt;
	private ResultSet resultSet;


	public static Connection con;

	public BankApp() {
		try {
			String url = "jdbc:mysql://localhost:3306/projectbank";

			String user = "root";

			String pwd = "Sreejarac@18";

			Class.forName("com.mysql.cj.jdbc.Driver");
			con = DriverManager.getConnection(url, user, pwd);
		}catch (Exception e) {
			// TODO: handle exception
		}
	}


	/**
	 * @return the bankId
	 */
	public int getBankId() {
		return bank_id;
	}
	/**
	 * @param bankId the bankId to set
	 */
	public void setBankId(int bankId) {
		this.bank_id = bank_id;
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
	 * @return the accno
	 */
	public int getAccno() {
		return accno;
	}
	/**
	 * @param accno the accno to set
	 */
	public void setAccno(int accno) {
		this.accno = accno;
	}
	/**
	 * @return the pin
	 */
	public int getPin() {
		return pin;
	}
	/**
	 * @param pin the pin to set
	 */
	public void setPin(int pin) {
		this.pin = pin;
	}
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
	 * @return the customer_name
	 */
	public String getCustomer_name() {
		return customer_name;
	}
	/**
	 * @param customer_name the customer_name to set
	 */
	public void setCustomer_name(String customer_name) {
		this.customer_name = customer_name;
	}
	/**
	 * @return the balance
	 */
	public int getBalance() {
		return balance;
	}
	/**
	 * @param balance the balance to set
	 */
	public void setBalance(int balance) {
		this.balance = balance;
	}
	/**
	 * @return the email
	 */
	public String getEmail() {
		return email;
	}
	/**
	 * @param email the email to set
	 */
	public void setEmail(String email) {
		email = email;
	}
	/**
	 * @return the phone
	 */
	public long getPhone() {
		return phone;
	}
	/**
	 * @param phone the phone to set
	 */
	public void setPhone(long phone) {
		phone = phone;
	}


	public boolean register() {

		try {
			pstmt = con.prepareStatement("insert into bankapp values(?,?,?,?,?,?,?,?,?,?)");

			pstmt.setInt(1, bank_id);

			pstmt.setString(2, bank_name);

			pstmt.setString(3, ifsc_code);

			pstmt.setInt(4, accno);

			pstmt.setInt(5, pin);

			pstmt.setInt(6, cust_id);

			pstmt.setString(7, customer_name);

			pstmt.setInt(8, balance);

			pstmt.setString(9, email);

			pstmt.setLong(10, phone);

			int x = pstmt.executeUpdate();

			if(x>0) {
				return true;
			}
			else {
				return false;
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	public boolean login() {
		try {
			pstmt = con.prepareStatement("Select * from bankapp where cust_id=? and pin=?");

			pstmt.setInt(1, cust_id);
			pstmt.setInt(2, pin);

			resultSet = pstmt.executeQuery();

			if(resultSet.next()==true) {			
				this.setCustomer_name(resultSet.getString("customer_name"));
				this.setAccno(resultSet.getInt("accno"));
				return true;
			}else {
				return false;
			}

		}catch(Exception e){
			e.printStackTrace();
		}
		return false;
	}
	public boolean CheckBalance() {
		try {
			pstmt = con.prepareStatement("select balance from bankapp where accno = ?");

			pstmt.setInt(1, accno);

			System.out.println(accno);

			resultSet = pstmt.executeQuery();

			if(resultSet.next()==true) {
				setBalance(resultSet.getInt("balance"));
				return true;


			}else {
				return false;
			}


		}catch(Exception e){
			e.printStackTrace();
		}
		return false;
	}
	public boolean ChangePasswords(int accno, int n_pwd) {
		try {
			try {
				String sql="update BankApp set pin=? where accno=? and pin=?";
				pstmt=con.prepareStatement(sql);
				pstmt.setInt(1,n_pwd);
				pstmt.setInt(2,accno);
				pstmt.setInt(3,pin);
				int x=pstmt.executeUpdate();
				if(x>0)
				{
					return true;
				}
				else
				{
					return false;
				}
				
			}catch(Exception e)
			{
				return false;
			}



			}catch(Exception e){
				e.printStackTrace();
			}
			return false;
		}


	/**
	 * @return the bank_id
	 */
	public int getBank_id() {
		return bank_id;
	}


	/**
	 * @param bank_id the bank_id to set
	 */
	public void setBank_id(int bank_id) {
		this.bank_id = bank_id;
	}


	/**
	 * @return the n_pwd
	 */
	public int getN_pwd() {
		return n_pwd;
	}


	/**
	 * @param n_pwd the n_pwd to set
	 */
	public void setN_pwd(int n_pwd) {
		this.n_pwd = n_pwd;
	}


	/**
	 * @return the old_pwd
	 */
	public int getOld_pwd() {
		return old_pwd;
	}


	/**
	 * @param old_pwd the old_pwd to set
	 */
	public void setOld_pwd(int old_pwd) {
		this.old_pwd = old_pwd;
	}

	}
