/**
 * Verification.java
 * 
 * This class tests the entered date
 */
package komi.date.validation;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.JOptionPane;

/**
 * @author Komi J Wodeke
 *
 */
public class Verification {

	private final String DATE_REGEX = "^\\d{2}\\/\\d{2}\\/\\d{4}$";

	/**
	 * To validate thirty days months
	 * 
	 * @param pDay
	 *            the entered day
	 * @return the test result
	 */
	private boolean isValidThirtyDaysMonth(int pDay) {
		return pDay <= 30;
	}

	/**
	 * To validate thirty-one days months
	 * 
	 * @param pDay
	 *            the entered day
	 * @return the test result
	 */
	private boolean isValidThirtyOneDaysMonth(int pDay) {
		return pDay <= 31;
	}

	/**
	 * To validate the second month
	 * 
	 * @param pDay
	 *            the entered day
	 * @param pYear
	 *            the entered year
	 * @return the test result
	 */
	private boolean isValidSecondMonth(int pDay, int pYear) {
		int test = pYear % 4;
		if (test == 0)
			return pDay > 0 && pDay <= 29;
		else
			return pDay > 0 && pDay <= 28;
	}

	/**
	 * To prompt for the input
	 * 
	 * @return the matched input
	 */
	private String getEnteredValue() {
		String message = "Enter the date (MM/DD/YYYY)";
		String enteredValue = "";
		Matcher matcher = null;

		do {
			enteredValue = JOptionPane.showInputDialog(message);
			if (enteredValue == null || enteredValue == "") {
				System.out.println("Input canceled!");
				System.exit(0);
			} else {
				Pattern pattern = Pattern.compile(DATE_REGEX);
				matcher = pattern.matcher(enteredValue);
			}
		} while (!matcher.matches());

		return enteredValue;
	}

	/**
	 * To validate the entered date
	 * 
	 * @param pEnteredValue
	 *            the entered value
	 * @return the test result
	 */
	private boolean isValidEnteredDate(String pEnteredValue) {
		boolean dateTest = false;

		String[] valueParts = pEnteredValue.split("/");
		int month = Integer.parseInt(valueParts[0]);
		int day = Integer.parseInt(valueParts[1]);
		int year = Integer.parseInt(valueParts[2]);

		if (month * day * year == 0)
			dateTest = false;
		else if (month > 12)
			dateTest = false;
		else if (month % 2 == 0) {
			if (month == 2)
				dateTest = isValidSecondMonth(day, year);
			else if (month == 8)
				dateTest = isValidThirtyOneDaysMonth(day);
			else if (month < 7)
				dateTest = isValidThirtyDaysMonth(day);
			else if (month > 8)
				dateTest = isValidThirtyOneDaysMonth(day);
		} else if (month <= 7)
			dateTest = isValidThirtyOneDaysMonth(day);
		else if (month > 8)
			dateTest = isValidThirtyDaysMonth(day);

		return dateTest;
	}

	/**
	 * To test the entered value
	 */
	public void makeTests() {
		String enteredDate = "";
		String result = "";

		do {
			// Get the date
			enteredDate = getEnteredValue();
			// Test the entered value
			if (isValidEnteredDate(enteredDate))
				result = enteredDate + " is valid";
			else
				result = enteredDate + " is invalid";
			// Display the test result
			JOptionPane.showMessageDialog(null, result);
		} while (true);
	}
}
