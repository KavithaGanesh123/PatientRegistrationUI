package com.patient.patientregistration;

import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.MouseAdapter;
import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.graphics.Font;
import org.eclipse.swt.graphics.FontData;
import org.eclipse.swt.layout.FormAttachment;
import org.eclipse.swt.layout.FormData;
import org.eclipse.swt.layout.FormLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.DateTime;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;

import com.patient.httpclient.PatientClient;
import com.patient.model.AddressListSO;
import com.patient.model.PatientDetailsSO;
import com.patient.model.PatientIdentifierSO;

public class Registration {

	 public Shell shellRegister;
	 public Text patientId;
	 public Text patientName;
	 public Text contactNumber;
	 public Text altContactNumber;
	 public Text identificationNumber;
	 public Text addressLine1;
	 public Text addressLine2;
	 public Text state;
	 public Text country;
	 public Text pincode;
	 public Text secAddressLine1;
	 public Text secAddressLine2;
	 public Text secState;
	 public Text secCountry;
	 public Text secPincode;
	 public Text altIdentificationNumber;

	 public DateTime patientDob;

	 public Combo identificationType;
	
	 public Combo altIdentificationType;
	 
	 public Button btnSubmitButton;
	 
	 public Button btnClear;
	 
	 Button btnPatientLocator;
	 
	 Label lblNewLabel;

	static PatientDetailsSO patientEntity;
	static PatientIdentifierSO identifierEntity;
	AddressListSO addressEntity;

	public static void main(String[] args) {

		Registration window = new Registration();
		window.open(true,false,false,patientEntity);

	}

	/**
	 * Open the window.
	 */
	public void open(boolean createPatient,boolean viewPatient,boolean updatePatient,PatientDetailsSO patientEntity) {
		
		// Create Shell (Window) from diplay
		// Shell shell = new Shell(display);

		Display display = Display.getDefault();
		final Shell shell = new Shell(display, SWT.CLOSE | SWT.TITLE | SWT.MIN);
		// final Composite composite = new Composite(shell, SWT.NONE);
		// shell.setEnabled(false);
		shell.setSize(1250, 790);
		shell.setText("Patient Registration Form");

		// Add fileds to the shell
		addFileds(shell,true,false,false);
		shell.open();
		
		if(viewPatient) {
			viewPatientDetails(patientEntity);
			lblNewLabel.setVisible(false);
			btnPatientLocator.setVisible(false);
			//disableAllFileds(shell);
			btnSubmitButton.setVisible(false);
			btnClear.setVisible(false);
			
		}
		
		if(updatePatient) {
			viewPatientDetails(patientEntity);
			lblNewLabel.setVisible(false);
			btnPatientLocator.setVisible(false);
			btnSubmitButton.setEnabled(true);
			btnSubmitButton.setText("UPDATE");
			btnClear.setVisible(false);
			//disableAllFileds(shell);
			
		}

		while (!shell.isDisposed()) {
			if (!display.readAndDispatch()) {
				display.sleep();
			}
		}
		//display.dispose();
	}

	/**
	 * Create contents of the window.
	 */
	public void addFileds(Shell shell,boolean createPatient,boolean viewPatient,boolean updatePatient) {


		lblNewLabel = new Label(shell, SWT.NONE);
		lblNewLabel.setBounds(323, 28, 374, 38);
		Font boldFont = new Font( lblNewLabel.getDisplay(), new FontData( "Arial", 16, SWT.BOLD ) );
		lblNewLabel.setFont( boldFont );
		lblNewLabel.setText("Patient Registration Form");
		
		Label lblPatientId = new Label(shell, SWT.NONE);
		lblPatientId.setBounds(14, 111, 101, 20);
		lblPatientId.setText("Patient ID");
		
		patientId = new Text(shell, SWT.BORDER);
		patientId.setBounds(231, 108, 141, 26);
		patientId.setEditable(false);
		
		Label lblNewLabel_1 = new Label(shell, SWT.NONE);
		lblNewLabel_1.setBounds(14, 174, 122, 20);
		lblNewLabel_1.setText("Patient Name");
		
		Label lblNewLabel_2 = new Label(shell, SWT.NONE);
		lblNewLabel_2.setBounds(14, 241, 113, 20);
		lblNewLabel_2.setText("Date Of Birth");
		
		patientDob = new DateTime(shell, SWT.BORDER);
		patientDob.setBounds(231, 222, 167, 28);
		
		patientName = new Text(shell, SWT.BORDER);
		patientName.setBounds(231, 171, 141, 26);
		
		Label lblContactNumber = new Label(shell, SWT.NONE);
		lblContactNumber.setBounds(13, 282, 146, 26);
		lblContactNumber.setText("Contact Number");
		
		Label lblAlternateContactNumber = new Label(shell, SWT.NONE);
		lblAlternateContactNumber.setBounds(13, 327, 179, 26);
		lblAlternateContactNumber.setText("Alternate Contact Number");
		
		contactNumber = new Text(shell, SWT.BORDER);
		contactNumber.setBounds(231, 279, 167, 26);
		
		altContactNumber = new Text(shell, SWT.BORDER);
		altContactNumber.setBounds(232, 324, 166, 26);
		
		Label lblPersonalIdentification = new Label(shell, SWT.NONE);
		lblPersonalIdentification.setBounds(13, 386, 179, 20);
		lblPersonalIdentification.setText("Personal Identification");
		
		identificationType = new Combo(shell, SWT.NONE);
		identificationType.setItems(new String[] {"AADHAR CARD", "PAN CARD", "DRIVING LICENSE"});
		identificationType.setBounds(231, 383, 167, 28);
		
		identificationNumber = new Text(shell, SWT.BORDER);
		identificationNumber.setBounds(231, 440, 167, 26);
		
		Label lblIdentificationNumber = new Label(shell, SWT.NONE);
		lblIdentificationNumber.setBounds(13, 443, 166, 20);
		lblIdentificationNumber.setText("Identification Number");
		
		Label lblAddress = new Label(shell, SWT.NONE);
		lblAddress.setBounds(424, 159, 110, 70);
		lblAddress.setText("Address\r\n[House/Apt no,\r\nLane/Locality]");
		
		addressLine1 = new Text(shell, SWT.BORDER);
		addressLine1.setBounds(558, 143, 190, 107);
		//addressLine1.setText("1201, Confident Group Leo3");
		
		Label lblNewLabel_3 = new Label(shell, SWT.NONE);
		lblNewLabel_3.setBounds(424, 293, 110, 60);
		lblNewLabel_3.setText("Address\r\n[Landmark,\r\nPlace,City]");
		
		addressLine2 = new Text(shell, SWT.BORDER);
		addressLine2.setBounds(558, 284, 192, 97);
		//addressLine2.setText("Manakkakadavu, Thengod, Cochin");
		
		Label lblNewLabel_4 = new Label(shell, SWT.NONE);
		lblNewLabel_4.setBounds(424, 416, 91, 20);
		lblNewLabel_4.setText("State");
		
		state = new Text(shell, SWT.BORDER);
		state.setBounds(558, 413, 189, 26);
		//state.setText("Kerala");
		
		Label lblNewLabel_5 = new Label(shell, SWT.NONE);
		lblNewLabel_5.setBounds(424, 477, 91, 20);
		lblNewLabel_5.setText("Country");
		
		country = new Text(shell, SWT.BORDER);
		country.setBounds(558, 471, 190, 26);
		//country.setText("India");
		
		Label lblNewLabel_6 = new Label(shell, SWT.NONE);
		lblNewLabel_6.setBounds(424, 531, 91, 20);
		lblNewLabel_6.setText("Pincode");
		
		pincode = new Text(shell, SWT.BORDER);
		pincode.setBounds(558, 525, 190, 26);
		//pincode.setText("682024");
		
		btnPatientLocator = new Button(shell, SWT.NONE);
		btnPatientLocator.setBounds(961, 26, 122, 30);
		btnPatientLocator.setText("Patient Locator");
		
		btnSubmitButton = new Button(shell, SWT.NONE);
		btnSubmitButton.setBounds(475, 627, 90, 30);
		if(createPatient) {
		btnSubmitButton.setText("SUBMIT");
		}else if(updatePatient) {
			btnSubmitButton.setText("UPDATE");
		}
		
		Label lblAlternateIdentification = new Label(shell, SWT.NONE);
		lblAlternateIdentification.setBounds(13, 503, 159, 20);
		lblAlternateIdentification.setText("Alternate Identification");
		
		altIdentificationType = new Combo(shell, SWT.NONE);
		altIdentificationType.setItems(new String[] {"AADHAR CARD", "PAN CARD", "DRIVING LICENSE"});
		altIdentificationType.setBounds(231, 495, 167, 28);
		
		Label lblPrimaryContactAddress = new Label(shell, SWT.NONE);
		lblPrimaryContactAddress.setBounds(430, 93, 224, 38);
		Font boldFont3 = new Font( lblNewLabel.getDisplay(), new FontData( "Arial", 9, SWT.BOLD ) );
		lblPrimaryContactAddress.setText("Primary Contact Address");
		lblPrimaryContactAddress.setFont(boldFont3);
		
		Label lblAddress_1 = new Label(shell, SWT.NONE);
		lblAddress_1.setText("Address\r\n[House/Apt no,\r\nLane/Locality]");
		lblAddress_1.setBounds(777, 146, 110, 70);
		
		Label lblNewLabel_3_1 = new Label(shell, SWT.NONE);
		lblNewLabel_3_1.setText("Address\r\n[Landmark,\r\nPlace,City]");
		lblNewLabel_3_1.setBounds(777, 282, 110, 60);
		
		Label lblNewLabel_4_1 = new Label(shell, SWT.NONE);
		lblNewLabel_4_1.setText("State");
		lblNewLabel_4_1.setBounds(777, 416, 91, 20);
		
		Label lblNewLabel_5_1 = new Label(shell, SWT.NONE);
		lblNewLabel_5_1.setText("Country");
		lblNewLabel_5_1.setBounds(777, 474, 91, 20);
		
		Label lblNewLabel_6_1 = new Label(shell, SWT.NONE);
		lblNewLabel_6_1.setText("Pincode");
		lblNewLabel_6_1.setBounds(777, 528, 91, 20);
		
		secAddressLine1 = new Text(shell, SWT.BORDER);
		secAddressLine1.setBounds(893, 143, 190, 107);
		
		secAddressLine2 = new Text(shell, SWT.BORDER);
		secAddressLine2.setBounds(891, 282, 192, 99);
		
		secState = new Text(shell, SWT.BORDER);
		secState.setBounds(894, 413, 189, 26);
		
		secCountry = new Text(shell, SWT.BORDER);
		secCountry.setBounds(893, 468, 190, 26);
		
		secPincode = new Text(shell, SWT.BORDER);
		secPincode.setBounds(893, 522, 190, 26);
		
		Label lblSecondaryContactAddress = new Label(shell, SWT.NONE);
		lblSecondaryContactAddress.setText("Secondary Contact Address");
		Font boldFont4 = new Font( lblNewLabel.getDisplay(), new FontData( "Arial", 9, SWT.BOLD ) );
		lblSecondaryContactAddress.setBounds(777, 93, 224, 38);
		lblSecondaryContactAddress.setFont(boldFont4);
		
		Label lblIdentificationNumber_1 = new Label(shell, SWT.NONE);
		lblIdentificationNumber_1.setText("Identification Number");
		lblIdentificationNumber_1.setBounds(13, 550, 166, 20);
		
		altIdentificationNumber = new Text(shell, SWT.BORDER);
		altIdentificationNumber.setBounds(231, 547, 167, 26);
		
//		patientName.setText("Laya");
//		contactNumber.setText("9645422387");
//		altContactNumber.setText("9645422387");
//		identificationType.setText("AADHAR CARD");
//		identificationNumber.setText("123454323456");
		
		btnClear = new Button(shell, SWT.NONE);
		btnClear.setBounds(643, 627, 90, 30);
		btnClear.setText("CLEAR");
		
		btnClear.addMouseListener(new MouseAdapter(){
		@Override
		public void mouseDown(MouseEvent e) {
			clearAllFileds(shell);

		}
	});

		btnSubmitButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseDown(MouseEvent e) {
//				if (patientEntity.getId() != null && !isView) {
//					updatePatientDetails(patientEntity);
//				}
				try {
					patientEntity = new PatientDetailsSO();
					
					boolean validFileds = getAllText(patientEntity, shell);
					if(btnSubmitButton.getText().toString().equalsIgnoreCase("UPDATE")) {
						if(validFileds){
							updatePatientDetails(patientEntity);
						}
					}
					else if (btnSubmitButton.getText().toString().equalsIgnoreCase("SUBMIT") && validFileds) {
						createPatientDetails(patientEntity);
					}
				} catch (ParseException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}

			}
		});
		btnSubmitButton.addListener(SWT.Selection, new Listener()

		{
			public void handleEvent(Event e) {
				// JUnitCore junit = new JUnitCore();
				// Result result = junit.run("your.class.here");
			}
		});
		
		
		
		btnPatientLocator.addListener(SWT.Selection, new Listener()

		{
			public void handleEvent(Event e) {
				// JUnitCore junit = new JUnitCore();
				// Result result = junit.run("your.class.here");
				PatientLocator locator = new PatientLocator();
				locator.open();
			}
		});

	}
	
	void clearAllFileds(Shell shell) {
		
		for(Control control : shell.getChildren()) {
		    if(control instanceof Combo){
		        ((Combo) control).setText("");
		    }else if(control instanceof Text){
		        ((Text) control).setText("");;
		    }
		}
		
	}
	
	
	void disableAllFileds(Shell shell) {
		
		for(Control control : shell.getChildren()) {
		    control.setEnabled(false);
		}
		
	}

	 void createPatientDetails(PatientDetailsSO patientEntity) {
		try {
			patientEntity = PatientClient.savePatientDetails(patientEntity);
			if (patientEntity != null) {
				populatePatientRegistration(patientEntity);
				openDialog("Patient Registered Successfully");
				btnSubmitButton.setEnabled(false);
				
			} else {
				openDialog("Create patient failed. Please try again with correct data");
			}
		} catch (IOException e1) {
			e1.printStackTrace();
		} catch (InterruptedException e1) {
			e1.printStackTrace();
		}
	}
	 
	 void updatePatientDetails(PatientDetailsSO patientEntity) {
			try {
				patientEntity = PatientClient.updatePatientDetails(patientEntity);
				if (patientEntity != null) {
					populatePatientRegistration(patientEntity);
					openDialog("Patient Updated Successfully");
					btnSubmitButton.setEnabled(false);
					
				} else {
					openDialog("Create patient failed. Please try again with correct data");
				}
			} catch (IOException e1) {
				e1.printStackTrace();
			} catch (InterruptedException e1) {
				e1.printStackTrace();
			}
		}
	 
	 void viewPatientDetails(PatientDetailsSO patientEntity) {
			try {
				
				List<PatientDetailsSO> patientEntityList = PatientClient.getPatientDetails(patientEntity);
				if (patientEntityList != null && patientEntityList.size()>0) {
					populatePatientRegistration(patientEntityList.get(0));
					
				} else {
					openDialog("View patient failed. Please try again with correct data");
				}
			} catch (IOException e1) {
				e1.printStackTrace();
			} catch (InterruptedException e1) {
				e1.printStackTrace();
			}
		}

	 public void populatePatientRegistration(PatientDetailsSO patientEntity) {

		 if(patientEntity.getPatientId() != 0)
		patientId.setText(Long.toString(patientEntity.getPatientId()));
		patientName.setText(patientEntity.getPatientName());
		patientDob.setData(patientEntity.getPatientDOB());
		// patientDob.setDate(patientEntity.getPatientDOB().getYear(),patientEntity.getPatientDOB().
		// getMonth(),patientEntity.getPatientDOB().getDay());
		contactNumber.setText(patientEntity.getContactNumber());
		altContactNumber.setText(patientEntity.getAlternateContactNumber());
		for (AddressListSO address : patientEntity.getPatientAddresses()) {
			if(addressLine1.getText().isBlank()) {
			addressLine1.setText(address.getAddressLine1()!=null ? address.getAddressLine1() : "");
			addressLine2.setText(address.getAddressLine2()!=null ? address.getAddressLine2() : "");
			state.setText(address.getState());
			country.setText(address.getCountry());
			pincode.setText(address.getPincode());
			}else {
				secAddressLine1.setText(address.getAddressLine1()!=null ? address.getAddressLine1() : "");
				secAddressLine2.setText(address.getAddressLine2()!=null ? address.getAddressLine2() : "");
				secState.setText(address.getState());
				secCountry.setText(address.getCountry());
				secPincode.setText(address.getPincode());
				
			}
		}

		for (PatientIdentifierSO identifier : patientEntity.getPatientIdentifiers()) {
			if(identificationType.getText().isBlank()) {
			identificationType.setText(identifier.getIdentifierType());
			identificationNumber.setText(identifier.getIdentifierNumber());
			}else {
				altIdentificationType.setText(identifier.getIdentifierType());
				altIdentificationNumber.setText(identifier.getIdentifierNumber());
			}
		}

	}

	 boolean getAllText(PatientDetailsSO patientEntity, Shell shell) throws ParseException {

		identifierEntity = new PatientIdentifierSO();

		boolean valid = validateFileds();

		if (valid) {
			
			patientEntity.setPatientName(patientName.getText());

			Calendar instance = Calendar.getInstance();
			patientDob = new DateTime(shell, SWT.BORDER);
			instance.set(Calendar.DAY_OF_MONTH, patientDob.getDay());
			instance.set(Calendar.MONTH, patientDob.getMonth());
			instance.set(Calendar.YEAR, patientDob.getYear());
			patientEntity.setPatientDOB(instance.getTime());
			
			patientEntity.setContactNumber(contactNumber.getText());
			
			patientEntity.setAlternateContactNumber(altContactNumber.getText());
			
			List<PatientIdentifierSO> identifiers = new ArrayList<>();
			
//			
//			for(Control control : shell.getChildren()) {
//			    if(control instanceof Combo){
//			        ((Combo) control).getText();
//			    }
//			}
			
			if(identificationType.getText()!=null && !identificationType.getText().isBlank()) {
				identifierEntity = new PatientIdentifierSO();
				identifierEntity.setIdentifierType(identificationType.getText());
				identifierEntity.setIdentifierNumber(identificationNumber.getText());
				identifiers.add(identifierEntity);
				}
			
			if(altIdentificationType.getText()!=null && !altIdentificationType.getText().isBlank() ) {
			identifierEntity = new PatientIdentifierSO();
			identifierEntity.setIdentifierType(altIdentificationType.getText());
			identifierEntity.setIdentifierNumber(altIdentificationNumber.getText());
			identifiers.add(identifierEntity);
			}
			
			patientEntity.setPatientIdentifiers(identifiers);

			List<AddressListSO> addresses = new ArrayList<>();
			AddressListSO currentAddress = new AddressListSO(
					addressLine1.getText() != null && !addressLine1.getText().isBlank() ? addressLine1.getText() : null,
					addressLine2.getText() != null && !addressLine2.getText().isBlank() ? addressLine2.getText() : null,
					state.getText(), country.getText(), pincode.getText());
			addresses.add(currentAddress);
			
			if(secAddressLine1.getText() !=null && !secAddressLine1.getText().isBlank()) {
			AddressListSO secondaryAddress = new AddressListSO(
					secAddressLine1.getText() != null && !secAddressLine1.getText().isBlank() ? secAddressLine1.getText() : null,
							secAddressLine2.getText() != null && !secAddressLine2.getText().isBlank() ? secAddressLine2.getText() : null,
					secState.getText(), secCountry.getText(), secPincode.getText());
			addresses.add(secondaryAddress);
			}
			patientEntity.setPatientAddresses(addresses);
		}
		return valid;

	}

	 boolean validateFileds() {
		String strNumberPattern = "^[0-9]{10}$";

		String strPincodePattern = "^[1-9][0-9]{5}$";
		
		
		
		boolean valid = false;
		if (patientName.getText() == null || patientName.getText().isBlank()) {
			openDialog("Patient name is Mandatory");
		} else if (contactNumber.getText() == null || contactNumber.getText().isBlank()) {
			openDialog("Contact Number is Mandatory");
		} else if ((contactNumber.getText() != null || !contactNumber.getText().isBlank())
				&& !contactNumber.getText().toString().matches(strNumberPattern)) {
			openDialog("Invalid Contact Number. Enter a 10-digit contact number");
		} else if ((altContactNumber.getText() != null || !altContactNumber.getText().isBlank())
				&& !altContactNumber.getText().toString().matches(strNumberPattern)) {
			openDialog("Invalid Alternate Contact Number.Enter a 10-digit contact number");
		} else if (addressLine1.getText() == null || addressLine1.getText().isBlank()) {
			openDialog("Patient Address is mandatory");
		} else if (state.getText() == null || state.getText().isBlank()) {
			openDialog("State is mandatory in patient address");
		} else if (country.getText() == null || country.getText().isBlank()) {
			openDialog("Country is mandatory in patient address");
		} else if (pincode.getText() == null || pincode.getText().isBlank()) {
			openDialog("Pincode is mandatory in patient address");
		} else if ((pincode.getText() == null || pincode.getText().isBlank())
				&& !pincode.getText().toString().matches(strPincodePattern)) {
			openDialog("Invalid Pincode");
		} else {
			valid = true;
		}

		return valid;

	}

	public static void openDialog(String message) {
		Shell dialog = new Shell(SWT.CLOSE | SWT.TITLE | SWT.MIN);
		dialog.setText("Alert");
		dialog.setSize(350, 100);
		FormLayout formLayout = new FormLayout();
		formLayout.marginWidth = 10;
		formLayout.marginHeight = 10;
		formLayout.spacing = 10;
		dialog.setLayout(formLayout);

		Label label = new Label(dialog, SWT.NONE);
		label.setText(message);
		FormData data = new FormData();
		label.setLayoutData(data);

		Button cancel = new Button(dialog, SWT.PUSH);
		cancel.setText("Close");
		data = new FormData();
		data.width = 60;
		data.right = new FormAttachment(100, 0);
		data.bottom = new FormAttachment(100, 0);
		cancel.setLayoutData(data);
		cancel.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseDown(MouseEvent e) {
				dialog.close();
			}
		});

		dialog.open();
	}

}
