package com.patient.model;


/**
 * @author KL105911
 *
 */
public class PatientAddress {


	private long patientAdrId;

	private String addressLine1;
	private String addressLine2;

	private String state;

	private String country;

	private String pincode;
	
	private String addrType;


	/**
	 * @return the patientAdrId
	 */
	public long getPatientAdrId() {
		return patientAdrId;
	}

	/**
	 * @param patientAdrId the patientAdrId to set
	 */
	public void setPatientAdrId(long patientAdrId) {
		this.patientAdrId = patientAdrId;
	}

	/**
	 * @return the addressLine1
	 */
	public String getAddressLine1() {
		return addressLine1;
	}

	/**
	 * @param addressLine1 the addressLine1 to set
	 */
	public void setAddressLine1(String addressLine1) {
		this.addressLine1 = addressLine1;
	}

	/**
	 * @return the addressLine2
	 */
	public String getAddressLine2() {
		return addressLine2;
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
	 * @return the addrType
	 */
	public String getAddrType() {
		return addrType;
	}

	/**
	 * @param addrType the addrType to set
	 */
	public void setAddrType(String addrType) {
		this.addrType = addrType;
	}


	

}
