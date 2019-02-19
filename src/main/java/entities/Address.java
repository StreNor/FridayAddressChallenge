package entities;

// TODO: Auto-generated Javadoc
/**
 * The Class Address.
 */
public class Address {

	/** The street. */
	private String street;
	
	/** The houseNumber. */
	private String houseNumber;
	
	
	/**
	 * Instantiates a new address.
	 *
	 * @param street the street
	 * @param houseNumber the houseNumber
	 */
	public Address(String street, String houseNumber) {
		super();
		this.street = street;
		this.houseNumber = houseNumber;
	}
	
	
	/**
	 * Gets the street.
	 *
	 * @return the street
	 */
	public String getStreet() {
		return street;
	}
	
	/**
	 * Sets the street.
	 *
	 * @param street the new street
	 */
	public void setStreet(String street) {
		this.street = street;
	}
	
	/**
	 * Gets the houseNumber.
	 *
	 * @return the houseNumber
	 */
	public String getHouseNumber() {
		return houseNumber;
	}
	
	/**
	 * Sets the houseNumber.
	 *
	 * @param houseNumber the new houseNumber
	 */
	public void setHouseNumber(String houseNumber) {
		this.houseNumber = houseNumber;
	}
	
	
}
