package utilities;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

import com.google.gson.Gson;
import com.jayway.jsonpath.JsonPath;

import entities.Address;

/**
 * The Class ComponentsGenerator.
 * 
 * It uses the address parser and address standardizer of Gisgraphy project (
 * free open source worldwide geocoder ) Address parsing is the process of
 * dividing a single address string into its individual component parts: House
 * number Street type Street name state country..
 * 
 * API URL:
 * "http://services.gisgraphy.com/addressparser/?address=address&country=country&format=json"
 * 
 * We use the Restful web service of address parser that takes in parameters
 * mainly the address as single string, json as return format and return all the
 * components seperatly It is advised to send the ISO 3166 country code of the
 * address as parameter ( &country="" ) in order to get proper results.
 * 
 * Notice that parsing is a complex process. An address can sometimes be
 * interpreted in many ways a,d it is the parser job to find the best one. The
 * Web service return in the body a confidence indicator: 
 * MAX: the address has been parsed without difficulties. 
 * MEDIUM: some difficulties has been found but the parser found how to disambiguate. 
 * MIN: Some difficulties has been
 * found and the parser failed to disabiguate.
 * 
 */
public class ComponentsGenerator {

	/**
	 * This method generate components from string address.
	
	 * @param address
	 *            the address
	 * @return the string
	 */
	public static String generateComponentsFromStringAddress(String full_address, String country_code) {

		// Specify the base URL to the RESTful web service
		RestAssured.baseURI = "http://services.gisgraphy.com/addressparser/?";

		// Get the RequestSpecification of the request that you want to sent
		// to the server. The server is specified by the BaseURI that we have
		// specified in the above step.
		RequestSpecification httpRequest = RestAssured.given();

		// Make a request to the server by specifying the method Type and the
		// method URL.
		// This will return the Response from the server. Store the response in
		// a variable.
		Response response = httpRequest.get("address="+full_address+"&country="+country_code+"&format=json");

		// Retrieve the body of the Response
		// get the Response body as a String.
		String bodyAsString = response.getBody().asString();

		// Parse the body and retrieve the street name
		String street;
		
		//Try catch block in case street name or house number forgotten PathNotFoundException is raised
		try {
			street = JsonPath.read(bodyAsString, "$.result[0].streetName");
		} catch (Exception e) {
			street="";
		}
		// Parse the body and retrieve the house number
		String houseNumber;
		try {
			houseNumber = JsonPath.read(bodyAsString, "$.result[0].houseNumber");
		} catch (Exception e) {
			houseNumber="";
		}

		// Create an address model class object and convert it to Json object
		// using GSON toJson
		Address addr = new Address(street, houseNumber);
		Gson gson = new Gson();
		String splitted_address = gson.toJson(addr);
		
		return splitted_address;

	}
}
