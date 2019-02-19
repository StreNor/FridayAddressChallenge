package init;

import utilities.ComponentsGenerator;


/**
 * The Class AddressProvider.
 */
public class Init {

	/**
	 * The main method.
	 *
	 * @param args the arguments
	 */
	public static void main(String[] args) {

		String full_address = "Musterstrasse 45";

		String splitted_address = ComponentsGenerator
				.generateComponentsFromStringAddress(full_address, null);

		System.out.println(splitted_address);

	}
}
