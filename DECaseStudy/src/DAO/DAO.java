package DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import com.mysql.jdbc.PreparedStatement;

public class DAO {
	static Connection con = null;
	static ResultSet rs = null;
	static Statement st = null;
	static PreparedStatement pstmt = null;

	static String url = "jdbc:mysql://localhost:3306/cdw_sapp?useSSL=false";
	static String username = "root";
	static String password = "root";

	public static void functionalRequirements1(int month, int year, int zipCode) {
		try {
			String query = "SELECT transaction_id,  month, day, year, cdw_sapp_creditcard.credit_card_no, cust_ssn, cust_zip,branch_code, transaction_value, transaction_type  \r\n" + "FROM cdw_sapp_customer \r\n"
					+ "	JOIN cdw_sapp_creditcard ON cdw_sapp_customer.SSN = cdw_sapp_creditcard.CUST_SSN\r\n"
					+ "WHERE MONTH = ?\r\n"
					+ "	AND YEAR = ?\r\n" 
					+ "	AND CUST_ZIP = ?\r\n"
					+ "GROUP BY DAY\r\n" 
					+ "ORDER BY DAY DESC; ";

			Class.forName("com.mysql.jdbc.Driver");
			java.sql.Connection con = DriverManager.getConnection(url, username, password);
			java.sql.PreparedStatement pstmt = con.prepareStatement(query);
			
			pstmt.setInt(1, month);
			pstmt.setInt(2, year);
			pstmt.setInt(3, zipCode);
			ResultSet rs = pstmt.executeQuery();
			System.out.println("\n+-------+---------------+-----------------------+---------------+--------+--------------+---------------+-------------------+");
			System.out.println("| ID \t| Date(M-D-YYY) | Credit Card Number \t| Customer SSN  | Zip \t | Branch Code  | Amount \t| Transaction Type  |");
			System.out.println("+-------+---------------+-----------------------+---------------+--------+--------------+---------------+-------------------+");
			while (rs.next()) {
				System.out.println("| " + rs.getInt(1) + "\t| " + rs.getInt(2) + "-" + rs.getInt(3)+"-"+ rs.getInt(4)+ "\t| "+rs.getString(5)+"\t| "+rs.getString(6)+"\t| "+rs.getString(7)+"  | "+rs.getInt(8)+"\t \t|   $"+rs.getDouble(9)+"\t| "+rs.getString(10)+"     |");
			}
			System.out.println("+-------+---------------+-----------------------+---------------+--------+--------------+---------------+-------------------+");
			 
			
		} catch (SQLException se) {
			se.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (st != null) {
					con.close();
				}
			} catch (SQLException se) {
			}
			try {
				if (con != null) {
					con.close();
				}
			} catch (SQLException se) {
				se.printStackTrace();
			}
		}
	}

	public static void functionalRequirements2(String transactionType) {
		try {
			String query = "SELECT TRANSACTION_TYPE, COUNT(*), SUM(TRANSACTION_VALUE) \r\n"
					+ "FROM cdw_sapp_creditcard\r\n" + "GROUP BY TRANSACTION_TYPE\r\n"
					+ "HAVING TRANSACTION_TYPE LIKE \'" + transactionType + "\';";

			Class.forName("com.mysql.jdbc.Driver");
			Connection con = DriverManager.getConnection(url, username, password);
			Statement st = con.createStatement();
			ResultSet rs = st.executeQuery(query);
			System.out.println("\n+-----------------------+-----------------------+---------------+");
			System.out.println("| Transaction Type \t| # of Transactions \t| Total Amount  |");
			System.out.println("+-----------------------+-----------------------+---------------+");
			while (rs.next()) {
				System.out.println("| " + rs.getString(1) + "\t \t| " + rs.getDouble(2) + " \t \t| $" + rs.getDouble(3) + "\t|");
			}
			System.out.println("+-----------------------+-----------------------+---------------+");
			 

		} catch (SQLException se) {
			se.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (st != null) {
					con.close();
				}
			} catch (SQLException se) {
			}
			try {
				if (con != null) {
					con.close();
				}
			} catch (SQLException se) {
				se.printStackTrace();
			}
		}
	}

	public static void functionalRequirements3(String stateNameAbbr) {

		try {
			String query = "SELECT BRANCH_NAME, BRANCH_STATE, COUNT(TRANSACTION_ID), SUM(TRANSACTION_VALUE)\r\n"
					+ "FROM cdw_sapp_branch \r\n"
					+ "	JOIN cdw_sapp_creditcard ON cdw_sapp_branch.BRANCH_CODE = cdw_sapp_creditcard.BRANCH_CODE\r\n"
					+ "GROUP BY BRANCH_STATE\r\n" + "HAVING BRANCH_STATE LIKE \'" + stateNameAbbr + "\';";

			Class.forName("com.mysql.jdbc.Driver");
			java.sql.Connection con = DriverManager.getConnection(url, username, password);

			Statement st = con.createStatement();
			ResultSet rs = st.executeQuery(query);
			System.out.println("+---------------+---------------+-----------------------+-----------------------+");
			System.out.println("| Branch Name \t| Branch State \t| # of Transactions \t| Amt of Transactions   |");
			System.out.println("+---------------+---------------+-----------------------+-----------------------+");
			while (rs.next()) {
				System.out.println("| "+ rs.getString(1) + "\t| " + rs.getString(2) + " \t \t| " + rs.getDouble(3) + " \t \t| $"
						+ rs.getDouble(4)+ "\t\t|");
			}
			System.out.println("+---------------+---------------+-----------------------+-----------------------+");
			 

		} catch (SQLException se) {
			se.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (st != null) {
					con.close();
				}
			} catch (SQLException se) {
			}
			try {
				if (con != null) {
					con.close();
				}
			} catch (SQLException se) {
				se.printStackTrace();
			}
		}
	}

	public static void functionalRequirements4(String firstName, String lastName, String ssn) {
		try {
			String query = "SELECT * FROM cdw_sapp_customer\r\n" + "WHERE FIRST_NAME LIKE \'" + firstName + "\'\r\n"
					+ "  AND LAST_NAME LIKE \'" + lastName + "\'\r\n" + "  AND SSN LIKE " + ssn + ";";

			Class.forName("com.mysql.jdbc.Driver");
			java.sql.Connection con = DriverManager.getConnection(url, username, password);

			Statement st = con.createStatement();
			ResultSet rs = st.executeQuery(query);
			System.out.println("\n+---------------+---------------+---------------+---------------+-----------------------+-----------------------------------------------------------------------+---------------+-----------------------+-----------------------+");
			System.out.println("| First Name \t| Middle Name \t| Last Name  \t| SSN   \t| Credit Card No \t"
					+ "| Address \t \t \t \t \t \t \t \t| Phone \t| Email \t \t| Last Updated          |");
			System.out.println("+---------------+---------------+---------------+---------------+-----------------------+-----------------------------------------------------------------------+---------------+-----------------------+-----------------------+");
			while (rs.next()) {
				System.out.println("| " + rs.getString(1) + "\t \t| " + rs.getString(2) + "\t| " + rs.getString(3) + " \t| "
						+ rs.getLong(4) + " \t| " + rs.getLong(5) + " \t| Apt. " + rs.getByte(6) + ", " + rs.getString(7)
						+ ", " + rs.getString(8) + ", " + rs.getString(9) + " " + rs.getString(10) + ", "
						+ rs.getLong(11) + " \t| " + rs.getLong(12) + " \t| " + rs.getString(13) + " \t| "
						+ rs.getString(14)+ " |");
			}
			System.out.println("+---------------+---------------+---------------+---------------+-----------------------+-----------------------------------------------------------------------+---------------+-----------------------+-----------------------+");
			 

		} catch (SQLException se) {
			se.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (st != null) {
					con.close();
				}
			} catch (SQLException se) {
			}
			try {
				if (con != null) {
					con.close();
				}
			} catch (SQLException se) {
				se.printStackTrace();
			}
		}
	}

	public static void functionalRequirements5(String ssn) {
		try {
			String query1 = "SELECT FIRST_NAME, MIDDLE_NAME, LAST_NAME, CUST_PHONE, CUST_EMAIL, APT_NO, STREET_NAME, CUST_CITY, CUST_STATE, CUST_ZIP FROM cdw_sapp_customer\r\n"
					+ "WHERE SSN LIKE \'" + ssn + "\'\r\n";

			Class.forName("com.mysql.jdbc.Driver");
			java.sql.Connection con = DriverManager.getConnection(url, username, password);

			Statement st = con.createStatement();
			ResultSet rs = st.executeQuery(query1);
			System.out.println("\n--- User Details BEFORE Update ---");
			System.out.println("+---------------+---------------+---------------+-------------------------------+---------------------------------------+");
			System.out.println("| Name \t\t\t\t| Phone Number \t| Email \t \t \t| Address \t\t\t\t|");
			System.out.println("+-------------------------------+---------------+-------------------------------+---------------------------------------+");
			while (rs.next()) {
				System.out.println("| " + rs.getString(1) + " " + rs.getString(2) + " " + rs.getString(3) + "\t \t| " + rs.getString(4) + " \t| "
						+ rs.getString(5) + "\t| Apt. " + rs.getString(6) + ", " + rs.getString(7)+ ", " + rs.getString(8)
						+ ", " + rs.getString(9)+ ", " + rs.getString(10));
			}
			System.out.println("+-------------------------------+---------------+-------------------------------+---------------------------------------+");
			
			 

		} catch (SQLException se) {
			se.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (st != null) {
					con.close();
				}
			} catch (SQLException se) {
			}
			try {
				if (con != null) {
					con.close();
				}
			} catch (SQLException se) {
				se.printStackTrace();
			}
		}
	}
	public static void functionalRequirements5name(String ssnCase1, String firstName, String middleName, String lastName) {
		try {
						
			
			Class.forName("com.mysql.jdbc.Driver");
			java.sql.Connection con = DriverManager.getConnection(url, username, password);

		
			
			String query2 = "UPDATE cdw_sapp_customer \r\n" + 
					" SET FIRST_NAME = \'" + firstName + "\', \r\n" + 
					"	 MIDDLE_NAME = \'" + middleName + "\',\r\n" + 
					"     LAST_NAME = \'" + lastName + "\'\r\n" + 
					" WHERE SSN = "+ ssnCase1 ;
			Statement st2 = con.createStatement();
			int rowsAffected = st2.executeUpdate(query2);

			System.out.println("\n+---------------------------------------+");
			System.out.println("|   User Details SUCCESSFULLY Updated   |\n"
							+  "| = = = = = = = = = = = = = = = = = = = |\n"
							+  "| Message: " + rowsAffected + " row affected.              |");
			System.out.println("+---------------------------------------+");
	        
			String query3 = "SELECT FIRST_NAME, MIDDLE_NAME, LAST_NAME, CUST_PHONE, CUST_EMAIL, APT_NO, STREET_NAME, CUST_CITY, CUST_STATE, CUST_ZIP FROM cdw_sapp_customer\r\n"
					+ "WHERE SSN LIKE \'" + ssnCase1 + "\'\r\n";
			Statement st3 = con.createStatement();
			ResultSet rs3 = st3.executeQuery(query3);
			System.out.println("\n--- User Details AFTER Update ---");
			System.out.println("+---------------+---------------+---------------+-------------------------------+---------------------------------------+");
			System.out.println("| Name \t\t\t\t| Phone Number \t| Email \t \t \t| Address \t\t\t\t|");
			System.out.println("+-------------------------------+---------------+-------------------------------+---------------------------------------+");
			while (rs3.next()) {
				System.out.println("| " + rs3.getString(1) + " " + rs3.getString(2) + " " + rs3.getString(3) + "\t \t| " + rs3.getString(4) + " \t| "
						+ rs3.getString(5) + "\t| Apt. " + rs3.getString(6) + ", " + rs3.getString(7)+ ", " + rs3.getString(8)
						+ ", " + rs3.getString(9)+ ", " + rs3.getString(10));
			}
			System.out.println("+-------------------------------+---------------+-------------------------------+---------------------------------------+");
			 
			
		} catch (SQLException se) {
			se.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (st != null) {
					con.close();
				}
			} catch (SQLException se) {
			}
			try {
				if (con != null) {
					con.close();
				}
			} catch (SQLException se) {
				se.printStackTrace();
			}
		}
	}
	
	public static void functionalRequirements5phoneNum(String ssnCase2, String phoneNum) {
		try {
						
			

			Class.forName("com.mysql.jdbc.Driver");
			java.sql.Connection con = DriverManager.getConnection(url, username, password);

		
			
			String query2 = "UPDATE cdw_sapp_customer \r\n" + 
					"SET CUST_PHONE = \'" + phoneNum+"\'\r\n" + 
					"WHERE SSN =  "+ssnCase2+";" ;
			Statement st2 = con.createStatement();
			int rowsAffected = st2.executeUpdate(query2);
			System.out.println("\n+---------------------------------------+");
			System.out.println("|   User Details SUCCESSFULLY Updated   |\n"
							+  "| = = = = = = = = = = = = = = = = = = = |\n"
							+  "| Message: " + rowsAffected + " row affected.              |");
			System.out.println("+---------------------------------------+");
	        
			String query3 = "SELECT FIRST_NAME, MIDDLE_NAME, LAST_NAME, CUST_PHONE, CUST_EMAIL, APT_NO, STREET_NAME, CUST_CITY, CUST_STATE, CUST_ZIP FROM cdw_sapp_customer\r\n"
					+ "WHERE SSN LIKE \'" + ssnCase2 + "\'\r\n";
			Statement st3 = con.createStatement();
			ResultSet rs3 = st3.executeQuery(query3);
			System.out.println("\n--- User Details AFTER Update ---");
			System.out.println("+---------------+---------------+---------------+-------------------------------+---------------------------------------+");
			System.out.println("| Name \t\t\t\t| Phone Number \t| Email \t \t \t| Address \t\t\t\t|");
			System.out.println("+-------------------------------+---------------+-------------------------------+---------------------------------------+");
			while (rs3.next()) {
				System.out.println("| " + rs3.getString(1) + " " + rs3.getString(2) + " " + rs3.getString(3) + "\t \t| " + rs3.getString(4) + " \t| "
						+ rs3.getString(5) + "\t| Apt. " + rs3.getString(6) + ", " + rs3.getString(7)+ ", " + rs3.getString(8)
						+ ", " + rs3.getString(9)+ ", " + rs3.getString(10));
			}
			System.out.println("+-------------------------------+---------------+-------------------------------+---------------------------------------+");
			
			
		} catch (SQLException se) {
			se.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (st != null) {
					con.close();
				}
			} catch (SQLException se) {
			}
			try {
				if (con != null) {
					con.close();
				}
			} catch (SQLException se) {
				se.printStackTrace();
			}
		}
	}
	
	public static void functionalRequirements5email(String ssnCase3, String email) {
		try {
						
			

			Class.forName("com.mysql.jdbc.Driver");
			java.sql.Connection con = DriverManager.getConnection(url, username, password);

			
			String query2 = "UPDATE cdw_sapp_customer \r\n" + 
					"SET CUST_EMAIL = \'" + email +"\'\r\n" + 
					"WHERE SSN =  "+ssnCase3+";" ;
			Statement st2 = con.createStatement();
			int rowsAffected = st2.executeUpdate(query2);

			System.out.println("\n+---------------------------------------+");
			System.out.println("|   User Details SUCCESSFULLY Updated   |\n"
							+  "| = = = = = = = = = = = = = = = = = = = |\n"
							+  "| Message: " + rowsAffected + " row affected.              |");
			System.out.println("+---------------------------------------+");
	        
			String query3 = "SELECT FIRST_NAME, MIDDLE_NAME, LAST_NAME, CUST_PHONE, CUST_EMAIL, APT_NO, STREET_NAME, CUST_CITY, CUST_STATE, CUST_ZIP FROM cdw_sapp_customer\r\n"
					+ "WHERE SSN LIKE \'" + ssnCase3 + "\'\r\n";
			Statement st3 = con.createStatement();
			ResultSet rs3 = st3.executeQuery(query3);
			System.out.println("\n--- User Details AFTER Update ---");
			System.out.println("+---------------+---------------+---------------+-------------------------------+---------------------------------------+");
			System.out.println("| Name \t\t\t\t| Phone Number \t| Email \t \t \t| Address \t\t\t\t|");
			System.out.println("+-------------------------------+---------------+-------------------------------+---------------------------------------+");
			while (rs3.next()) {
				System.out.println("| " + rs3.getString(1) + " " + rs3.getString(2) + " " + rs3.getString(3) + "\t \t| " + rs3.getString(4) + " \t| "
						+ rs3.getString(5) + "\t| Apt. " + rs3.getString(6) + ", " + rs3.getString(7)+ ", " + rs3.getString(8)
						+ ", " + rs3.getString(9)+ ", " + rs3.getString(10));
			}
			System.out.println("+-------------------------------+---------------+-------------------------------+---------------------------------------+");
			
			
		} catch (SQLException se) {
			se.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (st != null) {
					con.close();
				}
			} catch (SQLException se) {
			}
			try {
				if (con != null) {
					con.close();
				}
			} catch (SQLException se) {
				se.printStackTrace();
			}
		}
	}
	
	public static void functionalRequirements5addr( String ssnCase4, String aptNum, String streetAddr, String city,String state, String zip) {
		try {
						

			Class.forName("com.mysql.jdbc.Driver");
			java.sql.Connection con = DriverManager.getConnection(url, username, password);


			String query2 = "UPDATE cdw_sapp_customer \r\n" + 
					" SET APT_NO = \'" + aptNum + "\', \r\n" + 
					"	 STREET_NAME = \'" + streetAddr + "\',\r\n" + 
					"     CUST_CITY = \'" + city + "\',\r\n" + 
					"	 CUST_STATE = \'" + state + "\',\r\n" + 
					"     CUST_ZIP = \'" + zip + "\'\r\n" + 
					" WHERE SSN = "+ ssnCase4 ;
			Statement st2 = con.createStatement();
			int rowsAffected = st2.executeUpdate(query2);

			System.out.println("\n+---------------------------------------+");
			System.out.println("|   User Details SUCCESSFULLY Updated   |\n"
							+  "| = = = = = = = = = = = = = = = = = = = |\n"
							+  "| Message: " + rowsAffected + " row affected.              |");
			System.out.println("+---------------------------------------+");
	        
			String query3 = "SELECT FIRST_NAME, MIDDLE_NAME, LAST_NAME, CUST_PHONE, CUST_EMAIL, APT_NO, STREET_NAME, CUST_CITY, CUST_STATE, CUST_ZIP FROM cdw_sapp_customer\r\n"
					+ "WHERE SSN LIKE \'" + ssnCase4 + "\'\r\n";
			Statement st3 = con.createStatement();
			ResultSet rs3 = st3.executeQuery(query3);
			System.out.println("\n--- User Details AFTER Update ---");
			System.out.println("+---------------+---------------+---------------+-------------------------------+---------------------------------------+");
			System.out.println("| Name \t\t\t\t| Phone Number \t| Email \t \t \t| Address \t\t\t\t|");
			System.out.println("+-------------------------------+---------------+-------------------------------+---------------------------------------+");
			while (rs3.next()) {
				System.out.println("| " + rs3.getString(1) + " " + rs3.getString(2) + " " + rs3.getString(3) + "\t \t| " + rs3.getString(4) + " \t| "
						+ rs3.getString(5) + "\t| Apt. " + rs3.getString(6) + ", " + rs3.getString(7)+ ", " + rs3.getString(8)
						+ ", " + rs3.getString(9)+ ", " + rs3.getString(10));
			}
			System.out.println("+-------------------------------+---------------+-------------------------------+---------------------------------------+");
			
		} catch (SQLException se) {
			se.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (st != null) {
					con.close();
				}
			} catch (SQLException se) {
			}
			try {
				if (con != null) {
					con.close();
				}
			} catch (SQLException se) {
				se.printStackTrace();
			}
		}
	}

	public static void functionalRequirements6(String creditCardNum, String monthNum) {
		try {
			String query = "SELECT CREDIT_CARD_NO, SUM(TRANSACTION_VALUE) FROM cdw_sapp_creditcard\r\n"
					+ "WHERE month = " + monthNum + "\r\n" + "GROUP BY CREDIT_CARD_NO\r\n" + "HAVING CREDIT_CARD_NO = " + creditCardNum
					+ ";";

			Class.forName("com.mysql.jdbc.Driver");
			Connection con = DriverManager.getConnection(url, username, password);
			Statement st = con.createStatement();
			ResultSet rs = st.executeQuery(query);
			System.out.println("\n+-----------------------+---------------------+");
			System.out.println("| Credit Card Number \t| Monthly Sum Due     |");
			System.out.println("+-----------------------+---------------------+");
			while (rs.next()) {
				System.out.println("| " + rs.getString(1) + "\t| $" + rs.getDouble(2)+ " ");
			}
			System.out.println("+-----------------------+---------------------+");
			 

		} catch (SQLException se) {
			se.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (st != null) {
					con.close();
				}
			} catch (SQLException se) {
			}
			try {
				if (con != null) {
					con.close();
				}
			} catch (SQLException se) {
				se.printStackTrace();
			}
		}
	}

	public static void functionalRequirements7(String ssn, int startDay, int startMonth, int startYear, int endDay, int endMonth, int endYear) {
		try {
			String query = "SELECT TRANSACTION_ID, CONCAT(MONTH,\"-\",DAY,\"-\",YEAR) AS Day,CREDIT_CARD_NO,CUST_SSN,BRANCH_CODE,TRANSACTION_VALUE,TRANSACTION_TYPE\r\n" + 
					"FROM cdw_sapp_creditcard\r\n" + 
					"WHERE CUST_SSN = \'"+ssn +"\' AND  str_to_date(CONCAT(MONTH,\"-\",DAY, \"-\",YEAR),'%m-%d-%y') \r\n" + 
					"	BETWEEN str_to_date(\'"+startMonth +"-"+startDay +"-"+startYear +"\','%m-%d-%y' )AND str_to_date(\'"+endMonth +"-"+endDay +"-"+endYear +"\','%m-%d-%y')\r\n" + 
					"ORDER BY YEAR DESC,MONTH DESC,DAY DESC";

			Class.forName("com.mysql.jdbc.Driver");
			java.sql.Connection con = DriverManager.getConnection(url, username, password);

			Statement st = con.createStatement();
			ResultSet rs = st.executeQuery(query);
			System.out.println("\n+-------+---------------+-----------------------+---------------+---------------+-----------------------+-------------------+");
			System.out.println("| ID \t|Date \t \t| Credit Card Number \t| SSN \t \t| Branch Code\t| Amount \t\t| Transaction Type  |");
			System.out.println("+-------+---------------+-----------------------+---------------+---------------+-----------------------+-------------------+");
			while (rs.next()) {
				System.out.println("| " + rs.getString(1) + "\t| " + rs.getString(2) + "\t| " + rs.getString(3) + "\t| "
						+ rs.getString(4) + "\t| " + rs.getInt(5)+ "\t\t|   $" + rs.getDouble(6)+ "\t\t| " + rs.getString(7)+ "\t|");
			}
			System.out.println("+-------+---------------+-----------------------+---------------+---------------+-----------------------+-------------------+");
			 
			
		} catch (SQLException se) {
			se.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (st != null) {
					con.close();
				}
			} catch (SQLException se) {
			}
			try {
				if (con != null) {
					con.close();
				}
			} catch (SQLException se) {
				se.printStackTrace();
			}
		}
	}

}
