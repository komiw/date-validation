/**
 * Rider.java
 * 
 * This class runs the verification
 */
package komi.date.validation;

/**
 * @author Komi J Wodeke
 *
 */
public class Rider {
	// Create Verification instance
	static Verification sVerification = new Verification();

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// Make some tests
		sVerification.makeTests();
	}

}
