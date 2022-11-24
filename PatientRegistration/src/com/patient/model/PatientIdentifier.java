package com.patient.model;



/**
 * @author KL105911
 *
 */
public class PatientIdentifier {
	
	private long patientItentifierId; 
	
	private String identifierType;
	
	private String identifierNumber;
	
	


	/**
	 * @return the identifierType
	 */
	public String getIdentifierType() {
		return identifierType;
	}


	/**
	 * @param identifierType the identifierType to set
	 */
	public void setIdentifierType(String identifierType) {
		this.identifierType = identifierType;
	}


	/**
	 * @return the identifierNumber
	 */
	public String getIdentifierNumber() {
		return identifierNumber;
	}


	/**
	 * @param identifierNumber the identifierNumber to set
	 */
	public void setIdentifierNumber(String identifierNumber) {
		this.identifierNumber = identifierNumber;
	}


	/**
	 * @return the patientItentifierId
	 */
	public long getPatientItentifierId() {
		return patientItentifierId;
	}


	/**
	 * @param patientItentifierId the patientItentifierId to set
	 */
	public void setPatientItentifierId(long patientItentifierId) {
		this.patientItentifierId = patientItentifierId;
	}




}
