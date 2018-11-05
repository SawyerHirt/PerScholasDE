package Runner;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Scanner;

import DAO.DAO;

public class CustomerRunner {
	static Connection con = null;
	static Statement st = null;
	static ResultSet rs = null;
	static String str = null;

	static String url = "jdbc:mysql://localhost:3306/cdw_sapp?useSSL=false";
	static String username = "root";
	static String password = "root";

	public static void functionalRequirements4(Scanner userInput) {
		try {
			System.out.println("\n+------------------------------+");
			System.out.println("| Please input First Name:     |");
			String firstName = userInput.next();
			System.out.println("+------------------------------+");
			System.out.println("| Please input Last Name:      |");
			String lastName = userInput.next();
			System.out.println("+------------------------------+");
			System.out.println("| Please input SSN:            |");
			String ssn = userInput.next();

			if (ssn.length() == 9) {
				DAO.functionalRequirements4(firstName, lastName, ssn);
			} else {
				// do nothing
			}

		} finally {
			System.out.println("\n   +-------------------------------------------------------------------------+\n"
					+ "   |Disclaimer: If nothing appears either there are no details associated    |\n"
					+ "   | with the customer information you input or you input the information    |\n"
					+ "   | incorrectly. Please use the following format: First Name ex Beau, Last  |\n"
					+ "   | Name ex Woodard, SSN ex 123454202, etc.                                 |\n"
					+ "   +-------------------------------------------------------------------------+");

		}
	}

	public static void functionalRequirements5(Scanner userInput) {
		try {
			System.out.println("\n+------------------------------------------+");
			System.out.println("| Please input Social Security Number:     |");
			String ssn = userInput.next();

			do {
				if (ssn.length() == 9) {
					DAO.functionalRequirements5(ssn);
				} else {
					System.out.println("Anything else?");
				}

				int updateOptionInt = 0;
				System.out.println("\n+---------------------------------------+");
				System.out.println("| Please select a option to update:     |\n"
						+ "| 1. Name                               |\n" + "| 2. Phone                              |\n"
						+ "| 3. Email                              |\n" + "| 4. Address                            |\n"
						+ "| = = = = = = = = = = = = = = = = = = = |");

				System.out.println("+----Choose the option to update:-------+");

				do {
					System.out.println("(Input a number):");
					while (!userInput.hasNextInt()) {
						String input1 = userInput.next();
						System.out.printf("\"%s\" is not a valid choice. Please select"
								+ " a choice and input as a whole number. \n"
								+ "(For example... Name = 1, Phone = 2, etc.)", input1);
					}
					updateOptionInt = userInput.nextInt();
					switch (updateOptionInt) {
					case 1:
						System.out.println("\n+--------------------------------------------------+");
						System.out.println("| Please re-enter your Social Security Number:     |");
						String ssnCase1 = userInput.next();

						System.out.println("+--------------------------------------------------+");
						System.out.println("| Please enter the first name you want associated  |\n"
								+ "| with your account:                               |");
						String firstName = userInput.next();

						System.out.println("+--------------------------------------------------+");
						System.out.println("| Please enter the middle name you want associated |\n"
								+ "| with your account:                               |");
						String middleName = userInput.next();

						System.out.println("+--------------------------------------------------+");
						System.out.println("| Please enter the last name you want associated   |\n"
								+ "| with your account:                               |");
						String lastName = userInput.next();

						DAO.functionalRequirements5name(ssnCase1, firstName, middleName, lastName);
						break;
					case 2:
						System.out.println("\n+--------------------------------------------------+");
						System.out.println("| Please re-enter your Social Security Number:     |");
						String ssnCase2 = userInput.next();
						System.out.println("+---------------------------------------------------+");
						System.out.println("| Please enter the phone number you want associated |\n"
								+ "| with your account:                                |");
						String phoneNum = userInput.next();

						DAO.functionalRequirements5phoneNum(ssnCase2, phoneNum);
						break;
					case 3:

						System.out.println("\n+--------------------------------------------------+");
						System.out.println("| Please re-enter your Social Security Number:     |");
						String ssnCase3 = userInput.next();

						System.out.println("+----------------------------------------------+");
						System.out.println("| Please enter the email you want associated  |\n"
								+ "| with your account:                          |");
						String email = userInput.next();

						DAO.functionalRequirements5email(ssnCase3, email);
						break;

					case 4:
						System.out.println("\n+--------------------------------------------------+");
						System.out.println("| Please re-enter your Social Security Number:     |");
						String ssnCase4 = userInput.next();
						System.out.println("+--------------------------------------------------+");
						System.out.println("| Please enter the apartment number you want       |\n"
								+ "| associated with your account:                    |");
						String aptNum = userInput.next();
						System.out.println("+--------------------------------------------------+");
						System.out.println("| Please enter the street address you want         |\n"
								+ "| associated with your account:                    |");
						String streetAddr = userInput.next();
						System.out.println("+--------------------------------------------------+");
						System.out.println("| Please enter the city you want associated with   |\n"
								+ "| your account:                                    |");
						String city = userInput.next();
						System.out.println("+--------------------------------------------------+");
						System.out.println("| Please enter the state you want associated with  |\n"
								+ "| your account:                                    |");
						String state = userInput.next();
						System.out.println("+--------------------------------------------------+");
						System.out.println("| Please enter the zip code you want associated    |\n"
								+ "| with your account:                               |");
						String zip = userInput.next();
						DAO.functionalRequirements5addr(ssnCase4, aptNum, streetAddr, city, state, zip);
						break;

					}

				} while (updateOptionInt < 0 || updateOptionInt > 7);
			} while (ssn.length() != 9);

		} finally {
			System.out.println("\n   +-------------------------------------------------------------------------+\n"
					+ "   |Disclaimer: If nothing appears either there is no details associated     |\n"
					+ "   | with the social security number you input or you input the number       |\n"
					+ "   | incorrectly. Please use the following format: ex SSN = 123456789        |\n"
					+ "   +-------------------------------------------------------------------------+");

		}

	}

	public static void functionalRequirements6(Scanner userInput) {
		try {
			System.out.println("\n+---------------------------------------------+");
			System.out.println("| Please input a credit card number you would |\n"
					+ "| like to see the total of the month for:     |");

			String creditCardNum = userInput.next();
			if (creditCardNum.length() == 16) {
				System.out.println("\n+---------------------------------------------+");
				System.out.println("| Please input a month you  would like to see |\n"
						+ "| the totoal for:                             |");
				String monthNum = userInput.next();
				if (monthNum.length() == 1 || monthNum.length() == 2)
					DAO.functionalRequirements6(creditCardNum, monthNum);
			} else {
				// do nothing
			}

			System.out.println("\n   +-------------------------------------------------------------------------+\n"
					+ "   |Disclaimer: If nothing appears either there is no details associated     |\n"
					+ "   | with the credit card number you input or you input the number           |\n"
					+ "   | incorrectly. Please use the following format: ex) 4210653349028689      |\n"
					+ "   +-------------------------------------------------------------------------+");

		} finally {
			// do nothing
		}
	}

	public static void functionalRequirements7(Scanner userInput) {
		try {
			System.out.println("+------------------------------+");
			System.out.println("| Please input SSN:            |");
			String ssn = userInput.next();
			System.out.println("+------------------------------+");
			System.out.println("| Please input Start Day:      |");
			int startDay = userInput.nextInt();
			System.out.println("+------------------------------+");
			System.out.println("| Please input Start Month:    |");
			int startMonth = userInput.nextInt();
			System.out.println("+------------------------------+");
			System.out.println("| Please input Start Year:     |");
			int startYear = userInput.nextInt();
			System.out.println("+------------------------------+");
			System.out.println("| Please input End Day:        |");
			int endDay = userInput.nextInt();
			System.out.println("+------------------------------+");
			System.out.println("| Please input End Month:      |");
			int endMonth = userInput.nextInt();
			System.out.println("+------------------------------+");
			System.out.println("| Please input End Year:       |");
			int endYear = userInput.nextInt();

			if (ssn.length() == 9) {
				DAO.functionalRequirements7(ssn, startDay, startMonth, startYear, endDay, endMonth, endYear);
			} else {
				// do nothing
			}
			System.out.println("\n   +-------------------------------------------------------------------------+\n"
					+ "   |Disclaimer: If nothing appears either there are no details associated    |\n"
					+ "   | with the customer information you input or you input the information    |\n"
					+ "   | incorrectly. Please use the following format: SSN ex 123454202, Start   |\n"
					+ "   | or End Month/Day as MM or DD, Year as YYYY                              |\n"
					+ "   +-------------------------------------------------------------------------+");

		} finally {
			// do nothing
		}
	}
}
