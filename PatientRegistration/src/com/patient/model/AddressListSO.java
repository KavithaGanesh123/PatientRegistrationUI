package com.patient.model;

/**
 * @author KL105911
 *
 */
public class AddressListSO {

	private String addressLine1;
	private String addressLine2;

	private String state;

	private String country;

	private String pincode;

	/**
	 * @return the addressLine1
	 */
	public String getAddressLine1() {
		return addressLine1;
	}

	/**
	 * @return the addressLine2
	 */
	public String getAddressLine2() {
		return addressLine2;
	}

	/**
	 * @param addressLine1 the addressLine1 to set
	 */
	public void setAddressLine1(String addressLine1) {
		this.addressLine1 = addressLine1;
	}

	/**
	 * @param addressLine2 the addressLine2 to set
	 */
	public void setAddressLine2(String addressLine2) {
		this.addressLine2 = addressLine2;
	}

	/**
	 * @return the state
	 */
	public String getState() {
		return state;
	}

	/**
	 * @param state the state to set
	 */
	public void setState(String state) {
		this.state = state;
	}

	/**
	 * @return the country
	 */
	public String getCountry() {
		return country;
	}

	/**
	 * @param country the country to set
	 */
	public void setCountry(String country) {
		this.country = country;
	}

	/**
	 * @return the pincode
	 */
	public String getPincode() {
		return pincode;
	}

	/**
	 * @param pincode the pincode to set
	 */
	public void setPincode(String pincode) {
		this.pincode = pincode;
	}

	/**
	 * 
	 * Constructore to set address object
	 * 
	 * @param addressLine1
	 * @param addressLine2
	 * @param state
	 * @param country
	 * @param pincode
	 */
	public AddressListSO(String addressLine1, String addressLine2, String state, String country, String pincode) {

		this.addressLine1 = addressLine1;
		this.addressLine2 = addressLine1;
		this.state = state;
		this.country = country;
		this.pincode = pincode;
	}

	public AddressListSO() {
		// TODO Auto-generated constructor stub
	}

}
