package tests;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import utilities.ComponentsGenerator;

import com.google.gson.Gson;

import entities.Address;

/**
 * The Class addressTests.
 * This class is created for testing purposes
 * Run class as TestNG Test to execute below tests
 */
public class AddressVerificationTests {

	/** The gson. */
	Gson gson = new Gson();

	/**
	 * Verify output Json format.
	 */
	@Test(description = "This test verify that the output format is as expected")
	public void VerifyJsonFormat() {

		String input_full_address = "Musterstrasse 45";

		String expected_splitted_address = "{\"street\":\"Musterstrasse\",\"houseNumber\":\"45\"}";

		// calling for static method generateComponentsFromStringAddress of
		// ComponentsGenerator with params (addresse, country code) and result
		// as json string with splitted components.
		String actual_splitted_address = ComponentsGenerator
				.generateComponentsFromStringAddress(input_full_address, "DE");

		Assert.assertEquals(expected_splitted_address, actual_splitted_address);

	}

	/**
	 * Addresses. 
	 * TestNG DataProviders allows us to automatically run a test case multiple times with different input
	 *
	 * @return the object[][] following the pattern { "input_full_address", "country_code", "expected street_name", "expected street_number" }
	 */
	@DataProvider(name = "Addresses")
	public static Object[][] addresses() {

		return new Object[][] {

				// country code can be sent null but it s highly recommended to
				// specify especially for complex addresses
				// the country of the address if it doesnt exists in the
				// address  itself
				// the API will still work and will try to find the best result.

				// verify simple cases values
				{ "Musterstrasse 45", "", "Musterstrasse", "45" },
				// Verify complicated case values.
				{ "Auf der Vogelwiese 23 b", "DE", "Auf der Vogelwiese", "23 b" },
				// Verify complex case values.
				{ "4, rue de la revolution", "FR", "de la revolution", "4" },
				// null address provided
				{ "", "", "", "" }};

	}

	/**
	 * Verify body values.
	 *
	 * @param input_full_address
	 *            the input full address
	 * @param country_code
	 *            the country code
	 * @param street_name
	 *            the street name
	 * @param street_number
	 *            the street number
	 */
	@Test(dataProvider = "Addresses", description = "This test verify that the output values are as expected")
	public void VerifySimpleCaseValues(String input_full_address,
			String country_code, String street_name, String street_number) {

		String splitted_address = ComponentsGenerator
				.generateComponentsFromStringAddress(input_full_address,
						street_number);

		// Create an address model class from the Json object
		// using GSON fromJson
		Address address = gson.fromJson(splitted_address, Address.class);

		Assert.assertEquals(address.getStreet(), street_name);
		Assert.assertEquals(address.getHouseNumber(), street_number);

	}

}
