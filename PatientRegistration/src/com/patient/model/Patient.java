package com.patient.model;

import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;


/**
 * @author KL105911
 *
 */
public class Patient {

	private long patientId;

	private String patientName;

	 @JsonFormat(pattern="dd/MM/yyyy HH:mm:ss.SSS")
	private Date patientDOB;


	private List<PatientIdentifier> patientIdentifier;


	private List<PatientAddress> patientAddresses;

	private String contactNumber;

	private String alternateContactNumber;

	/**
	 * @return the patientName
	 */
	public String getPatientName() {
		return patientName;
	}

	/**
	 * @param patientName the patientName to set
	 */
	public void setPatientName(String patientName) {
		this.patientName = patientName;
	}

	/**
	 * @return the patientDOB
	 */
	public Date getPatientDOB() {
		return patientDOB;
	}

	/**
	 * @param patientDOB the patientDOB to set
	 */
	public void setPatientDOB(Date patientDOB) {
		this.patientDOB = patientDOB;
	}

	/**
	 * @return the contactNumber
	 */
	public String getContactNumber() {
		return contactNumber;
	}

	/**
	 * @param contactNumber the contactNumber to set
	 */
	public void setContactNumber(String contactNumber) {
		this.contactNumber = contactNumber;
	}

	/**
	 * @return the alternateContactNumber
	 */
	public String getAlternateContactNumber() {
		return alternateContactNumber;
	}

	/**
	 * @param alternateContactNumber the alternateContactNumber to set
	 */
	public void setAlternateContactNumber(String alternateContactNumber) {
		this.alternateContactNumber = alternateContactNumber;
	}


	/**
	 * @return the patientId
	 */
	public long getPatientId() {
		return patientId;
	}

	/**
	 * @param patientId the patientId to set
	 */
	public void setPatientId(long patientId) {
		this.patientId = patientId;
	}

	/**
	 * @return the patientIdentifier
	 */
	public List<PatientIdentifier> getPatientIdentifier() {
		return patientIdentifier;
	}

	/**
	 * @param patientIdentifier the patientIdentifier to set
	 */
	public void setPatientIdentifier(List<PatientIdentifier> patientIdentifier) {
		this.patientIdentifier = patientIdentifier;
	}

	/**
	 * @return the patientAddresses
	 */
	public List<PatientAddress> getPatientAddresses() {
		return patientAddresses;
	}

	/**
	 * @param patientAddresses the patientAddresses to set
	 */
	public void setPatientAddresses(List<PatientAddress> patientAddresses) {
		this.patientAddresses = patientAddresses;
	}

}
