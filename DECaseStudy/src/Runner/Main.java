package Runner;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {

	public static void printMenu() {
		System.out.println("+-----------------------------------------------------+");
		System.out.println("| 1. To display the transactions made by customers    |\n"
				+ "|    living in a given zipcode for a given month and  |\n"
				+ "|    year. Order by day in descending order.          |");
		System.out.println("| 2. To display the number and total values of        |\n"
				+ "|    transactions  for a given type.                  |");
		System.out.println("| 3. To display the number and total values of        |\n"
				+ "|    transactions for branches in a given state.      |");
		System.out.println("| 4. To check the existing account details of a       |\n"
				+ "|    customer.                                        |");
		System.out.println("| 5. To modify the existing account details of a      |\n"
				+ "|    customer.                                        |");
		System.out.println("| 6. To generate monthly bill for a credit card       |\n"
				+ "|    number for a given month and year.               |");
		System.out.println("| 7. To display the transactions made by a customer   | \n"
				+ "|    between two dates. Order by year, month, and day | \n"
				+ "|    in descending order.                             |");
		System.out.println("| 8. Exit the program.                                |");
		System.out.println("| = = = = = = = = = = = = = = = = = = = = = = = = = = |\n"
				+ "+------Please choose your numerical selection---------+\n ");

	}

	public static void main(String[] args) {

		Scanner userInput = new Scanner(System.in);
		int mainMenuNum = 0;
		boolean isValid = false;
		do {
			printMenu();
			try {
				mainMenuNum = userInput.nextInt();

				if (mainMenuNum > 0 && mainMenuNum < 9) {
					switch (mainMenuNum) {
					case 1:
						TransactionRunner.functionalRequirements1(userInput);
						userInput.nextLine();
						isValid = true;
						break;
					case 2:
						TransactionRunner.functionalRequirements2(userInput);
						userInput.nextLine();
						isValid = true;
						break;
					case 3:
						TransactionRunner.functionalRequirements3(userInput);
						userInput.nextLine();
						isValid = true;
						break;
					case 4:
						CustomerRunner.functionalRequirements4(userInput);
						userInput.nextLine();
						isValid = true;
						break;
					case 5:
						CustomerRunner.functionalRequirements5(userInput);
						userInput.nextLine();
						isValid = true;
						break;
					case 6:
						CustomerRunner.functionalRequirements6(userInput);
						userInput.nextLine();
						isValid = true;
						break;
					case 7:
						CustomerRunner.functionalRequirements7(userInput);
						userInput.nextLine();
						isValid = true;
						break;
					case 8:
						System.out.println("\n+------------------------------------+");
						System.out.println("| Exiting the program. Thank You!    |");
						System.out.println("+------------------------------------+");
						isValid = true;
						break;
					}
				} else {
					System.out.println("That is not  a valid option. Please use whole numbers between 1 and 8.");
				}

			} catch (InputMismatchException ime) {
				userInput.nextLine();
				System.out.println("That is not  a valid option. Please use whole numbers between 1 and 8.");
				continue;
			} catch (Exception e) {
				userInput.nextLine();
				System.out.println("That is not  a valid option. Please use whole numbers between 1 and 8.");
				continue;
			}
		} while (!isValid);
	}


}
