package Runner;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Scanner;

import com.mysql.jdbc.PreparedStatement;

import DAO.DAO;

public class TransactionRunner {
	static Connection con = null;
	static ResultSet rs = null;
	static Statement st = null;
	static PreparedStatement pstmt = null;

	static String url = "jdbc:mysql://localhost:3306/cdw_sapp?useSSL=false";
	static String username = "root";
	static String password = "root";

	public static void functionalRequirements1(Scanner userInput) {
		try {
			int month = 0;
			int year = 0;
			int zipCode = 0;
			do {
				System.out.println("\n+------------------------------------+");
				System.out.println("| Please input month (Use a number): |");
				while (!userInput.hasNextInt()) {
					String input1 = userInput.next();
					System.out.printf("\"%s\" is not a valid choice. Please select"
							+ " a month and type in a numerical format. \n"
							+ "(For example... January = 1, February = 2, etc.)", input1);
				}
				month = userInput.nextInt();

			} while (month < 0 || month > 12);

			do {

				System.out.println("\n+------------------------------------+");
				System.out.println("| Please input year (Use a number):  |");
				while (!userInput.hasNextInt()) {
					String input2 = userInput.next();
					System.out.printf("\"%s\" is not a valid choice. Please select"
							+ " a month and type in a numerical format. \n"
							+ "(For example... January = 1, February = 2, etc.)", input2);
				}
				year = userInput.nextInt();

			} while (year < 1950 || year > 2018);

			do {
				System.out.println("\n+------------------------------------+");
				System.out.println("| Please input Zip Code:             |");
				while (!userInput.hasNextInt()) {
					String input3 = userInput.next();
					System.out.printf("\"%s\" is not a valid choice. Please select"
							+ " a month and type in a numerical format. \n" + "(For example... .)", input3);
				}
				zipCode = userInput.nextInt();

			} while (zipCode < 0 || zipCode > 99999);

			DAO.functionalRequirements1(month, year, zipCode);
		} finally {
			System.out.println("\n   +---------------------------------------------------------------------------+\n"
					+ "   |Disclaimer: If nothing appears either there are no results associated      |\n"
					+ "   | with your input or you have incorrectly entered inputs in the console.    |\n"
					+ "   | Please use the following format: Months as M or MM, ex 1 (as January) or  |\n"
					+ "   | 12 (as December); Year as YYYY, ex 2018; and Zip Code as ZZZZZ, ex 12345. |\n"
					+ "   +---------------------------------------------------------------------------+");
		}
	}

	public static void functionalRequirements2(Scanner userInput) {
		try {
			int transactionTypeInt = 0;
			String transactionType = "";
			System.out.println("+---------------------------------------+");
			System.out.println("| Please select a Transaction Type:     |\n"
					+ "| 1. Education                          |\n" + "| 2. Entertainment                      |\n"
					+ "| 3. Grocery                            |\n" + "| 4. Gas                                |\n"
					+ "| 5. Bills                              |\n" + "| 6. Test                               |\n"
					+ "| 7. Healthcare                         |\n" + "| = = = = = = = = = = = = = = = = = = = |");

			System.out.println("+----Choose the Transaction Type:-------+");

			do {
				System.out.println("(Input a number):");
				while (!userInput.hasNextInt()) {
					String input1 = userInput.next();
					System.out.printf(
							"\"%s\" is not a valid choice. Please select" + " a choice and input as a whole number. \n"
									+ "(For example... Education = 1, Entertainment = 2, etc.)",
							input1);
				}
				transactionTypeInt = userInput.nextInt();

			} while (transactionTypeInt < 0 || transactionTypeInt > 7);

			switch (transactionTypeInt) {
			case 1:
				transactionType = "Education";
				break;
			case 2:
				transactionType = "Entertainment";
				break;
			case 3:
				transactionType = "Grocery";
				break;
			case 4:
				transactionType = "Gas";
				break;
			case 5:
				transactionType = "Bills";
				break;
			case 6:
				transactionType = "Test";
				break;
			case 7:
				transactionType = "Healthcare";
				break;
			}

			// String transactionType = userInput.nextLine();
			DAO.functionalRequirements2(transactionType);

		} finally {
			System.out.println("\n   +-------------------------------------------------------------------------+\n"
					+ "   |Disclaimer: If nothing appears either there is no type associated with   |\n"
					+ "   | the your input or you input the transaction type value incorrectly.     |\n"
					+ "   | Please use the following format: Education as 1, Entertainment as 2,    |\n"
					+ "   | Grocery as 3, etc.                                                      |\n"
					+ "   +-------------------------------------------------------------------------+");
		}
	}

	public static void functionalRequirements3(Scanner userInput) {
		try {
			String[] checkState = { "MN", "IL", "NY", "FL", "PA", "NJ", "CT", "OH", "MI", "KY", "MD", "WA", "CA", "TX",
					"NC", "VA", "GA", "MT", "AR", "MS", "WI", "IN", "SC", "MA", "IA", "AL" };

			System.out.println("+---------------------------------------------+");
			System.out.println("| Please input State Name Abbreviation:       |\n"
					+ "| (eg. TX, AR, NY, etc...)                    |\n");
			String stateNameAbbr = userInput.next();
			for (String n : checkState) {
				if (stateNameAbbr.equals(n)) {
					DAO.functionalRequirements3(stateNameAbbr);
				}
			}

		} finally {
			System.out.println("\n   +-------------------------------------------------------------------------+\n"
					+ "   |Disclaimer: If nothing appears either there is no branch associated with |\n"
					+ "   |the state abbreviation you input or you input the state abbreviation     |\n"
					+ "   |incorrectly. Please use the following format: Texas as TX, Arizona       |\n"
					+ "   |as AR, New York as NY, etc.                                              |\n"
					+ "   +-------------------------------------------------------------------------+");

		}
	}
}
