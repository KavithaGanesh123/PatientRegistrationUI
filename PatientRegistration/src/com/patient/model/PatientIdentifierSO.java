package com.patient.model;

/**
 * @author KL105911
 *
 */
public class PatientIdentifierSO {
	
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
	 * @return the identifierNumber
	 */
	public String getIdentifierNumber() {
		return identifierNumber;
	}

	/**
	 * @param identifierType the identifierType to set
	 */
	public void setIdentifierType(String identifierType) {
		this.identifierType = identifierType;
	}

	/**
	 * @param identifierNumber the identifierNumber to set
	 */
	public void setIdentifierNumber(String identifierNumber) {
		this.identifierNumber = identifierNumber;
	}
	
	
	 /**
	  * 
	  * Constructor to set patientIdentifier schema object
	  * 
	 * @param identifierType
	 * @param identifierNumber
	 */
	public PatientIdentifierSO(String identifierType, String identifierNumber) {


	        this.identifierType = identifierType;
	        this.identifierNumber = identifierNumber;             
	    }

	public PatientIdentifierSO() {
		// TODO Auto-generated constructor stub
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
