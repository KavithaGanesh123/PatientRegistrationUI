package com.patient.patientregistration;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.graphics.Font;
import org.eclipse.swt.graphics.FontData;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.widgets.TableItem;
import org.eclipse.swt.widgets.Text;

import com.patient.httpclient.PatientClient;
import com.patient.model.PatientDetailsSO;
import com.patient.model.PatientIdentifierSO;

public class PatientLocator {

	protected Shell shell;
	private Table table;
	Text searchPatientId;
	Text searchPatientName;
	Text searchPatientIdentifier;

	/**
	 * Launch the application.
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			PatientLocator window = new PatientLocator();
			window.open();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Open the window.
	 */
	public void open() {
		Display display = Display.getDefault();
		final Shell shell = new Shell(display, SWT.CLOSE | SWT.TITLE | SWT.MIN);
		
		addFileds(shell);

		Button btnRegister = new Button(shell, SWT.NONE);
		btnRegister.setBounds(10, 8, 90, 30);
		btnRegister.setText("REGISTER");
		
		btnRegister.addListener(SWT.Selection, new Listener()

		{
			public void handleEvent(Event e) {
				Registration registration = new Registration();
				shell.close();
				display.dispose();
				registration.open(true, false, false, null);
				
			}
		});
		
		shell.open();
		shell.layout();
		while (!shell.isDisposed()) {
			if (!display.readAndDispatch()) {
				display.sleep();
			}
		}
	}

	/**
	 * Create contents of the window.
	 */
	public void addFileds(Shell shell) {
		

		shell.setSize(766, 664);
		shell.setText("SWT Application");

		table = new Table(shell, SWT.BORDER | SWT.FULL_SELECTION);
		table.setBounds(35, 144, 684, 407);
		table.setHeaderVisible(true);
		table.setLinesVisible(true);
		
		table.addListener(SWT.Selection, new Listener() {
            public void handleEvent(Event event) {
                if (event.detail == SWT.CHECK) {
                    System.out.println("You checked " + event.item);
                } 
                else {
                    System.out.println("You selected " + event.item);
                    TableItem ti  = (TableItem)event.item;
                    ti.setChecked(!ti.getChecked());
                }
            }
        });

		TableColumn tblclmnPatientId = new TableColumn(table, SWT.NONE);
		tblclmnPatientId.setWidth(75);
		tblclmnPatientId.setText("ID");

		TableColumn tblclmnName = new TableColumn(table, SWT.NONE);
		tblclmnName.setWidth(100);
		tblclmnName.setText("Name");

		TableColumn tblclmnNewColumn = new TableColumn(table, SWT.NONE);
		tblclmnNewColumn.setWidth(89);
		tblclmnNewColumn.setText("DOB");

		TableColumn tblclmnNewColumn_1 = new TableColumn(table, SWT.NONE);
		tblclmnNewColumn_1.setWidth(100);
		tblclmnNewColumn_1.setText("Contact No");

		TableColumn tblclmnAddress = new TableColumn(table, SWT.NONE);
		tblclmnAddress.setWidth(132);
		tblclmnAddress.setText("Address");

		TableColumn tblclmnState = new TableColumn(table, SWT.NONE);
		tblclmnState.setWidth(100);
		tblclmnState.setText("State");

		TableColumn tblclmnPincode = new TableColumn(table, SWT.NONE);
		tblclmnPincode.setWidth(81);
		tblclmnPincode.setText("Pincode");

		searchPatientId = new Text(shell, SWT.BORDER);
		searchPatientId.setBounds(35, 92, 165, 26);

		Label lblPatientLocator = new Label(shell, SWT.NONE);
		lblPatientLocator.setBounds(274, 10, 267, 31);
		Font boldFont = new Font(lblPatientLocator.getDisplay(), new FontData("Arial", 12, SWT.BOLD));
		lblPatientLocator.setFont(boldFont);
		lblPatientLocator.setText("         Patient Locator");

		searchPatientName = new Text(shell, SWT.BORDER);
		searchPatientName.setBounds(234, 92, 186, 26);

		searchPatientIdentifier = new Text(shell, SWT.BORDER);
		searchPatientIdentifier.setBounds(457, 92, 170, 26);

		Label lblPatientId = new Label(shell, SWT.NONE);
		lblPatientId.setBounds(35, 48, 165, 20);
		lblPatientId.setText("Patient ID");

		Label lblPatientName = new Label(shell, SWT.NONE);
		lblPatientName.setBounds(234, 47, 145, 20);
		lblPatientName.setText("Patient Name");

		Label lblPatientIdentifier = new Label(shell, SWT.NONE);
		lblPatientIdentifier.setBounds(457, 47, 146, 20);
		lblPatientIdentifier.setText("Patient Identifier");

		Button btnSearch = new Button(shell, SWT.NONE);
		btnSearch.setBounds(643, 88, 90, 30);
		Font boldFont1 = new Font(btnSearch.getDisplay(), new FontData("Arial", 7, SWT.BOLD));
		btnSearch.setFont(boldFont1);
		btnSearch.setText("SEARCH");

		Button btnViewPatient = new Button(shell, SWT.NONE);
		btnViewPatient.setBounds(84, 573, 130, 30);
		btnViewPatient.setText("View Patient");
		btnViewPatient.setEnabled(false);
		
		btnViewPatient.addListener(SWT.Selection, new Listener()

		{
			public void handleEvent(Event e) {
				PatientDetailsSO patientEntity = new PatientDetailsSO();
				TableItem[] tableValue = table.getSelection();
				if (tableValue.length != 0) {
					for (TableItem item : tableValue) {

						patientEntity.setPatientId(Long.valueOf(item.getText(0)));
						Registration registration = new Registration();
						registration.open(false, true, false, patientEntity);
					}
				} else {
					Registration.openDialog("Please select a patient");
				}

			}
		});
		
		

		Button btnUpdateButton = new Button(shell, SWT.NONE);
		btnUpdateButton.setBounds(307, 573, 145, 30);
		btnUpdateButton.setText("Update Patient");
		btnUpdateButton.setEnabled(false);
		
		btnUpdateButton.addListener(SWT.Selection, new Listener()

		{
			public void handleEvent(Event e) {
				PatientDetailsSO patientEntity = new PatientDetailsSO();
				TableItem[] tableValue = table.getSelection();
				if (tableValue.length != 0) {
					for (TableItem item : tableValue) {

						patientEntity.setPatientId(Long.valueOf(item.getText(0)));
						Registration registration = new Registration();
						registration.open(false, false, true, patientEntity);
					}
				} else {
					Registration.openDialog("Please select a patient");
				}

			}
		});

		Button btnRemoveButton = new Button(shell, SWT.NONE);
		btnRemoveButton.setBounds(529, 573, 159, 30);
		btnRemoveButton.setText("Remove Patient");
		btnRemoveButton.setEnabled(false);
		
		
		btnRemoveButton.addListener(SWT.Selection, new Listener()

		{
			public void handleEvent(Event e) {
				try {
					removePatientDetails(table.getSelection());
					table.remove(table.getSelectionIndices());
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (InterruptedException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		
		table.addListener(SWT.Selection, new Listener() {
            public void handleEvent(Event event) {
                if (event.detail == SWT.CHECK) {
                    System.out.println("You checked " + event.item);
                } 
                else {
                    System.out.println("You selected " + event.item);
                    TableItem ti  = (TableItem)event.item;
                    ti.setChecked(!ti.getChecked());
                    btnViewPatient.setEnabled(true);
                    btnUpdateButton.setEnabled(true);
                    btnRemoveButton.setEnabled(true);
                }
            }
        });

		btnSearch.addListener(SWT.Selection, new Listener()

		{
			public void handleEvent(Event e) {
				try {
					getPatientDetails();
					btnViewPatient.setEnabled(false);
                    btnUpdateButton.setEnabled(false);
                    btnRemoveButton.setEnabled(false);
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (InterruptedException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});

	}
	
	public void removePatientDetails(TableItem[] tableItems) throws IOException, InterruptedException {
		
		PatientDetailsSO patientEntity = new PatientDetailsSO();
		
		for(TableItem item: tableItems) {
			
			patientEntity.setPatientId(Long.valueOf(item.getText(0)));
			
		}
		
		String msg=PatientClient.removePatientDetails(patientEntity);
		
		if(msg.contains("Patient Successfully Deleted")) {
			Registration.openDialog(msg);
			
		}
		
		
		
	}

	public void getPatientDetails() throws IOException, InterruptedException {

		PatientDetailsSO patientEntity = new PatientDetailsSO();
		PatientIdentifierSO identifierEntity = new PatientIdentifierSO();
		List<PatientIdentifierSO> identifiersList = new ArrayList<>();
		List<PatientDetailsSO> patientsList = new ArrayList<>();

		if (searchPatientIdentifier.getText() != null && !searchPatientIdentifier.getText().isBlank()) {
			identifierEntity.setIdentifierNumber(searchPatientIdentifier.getText().toString());
			identifiersList.add(identifierEntity);
			patientEntity.setPatientIdentifiers(identifiersList);

		}
		if (searchPatientId.getText() != null && !searchPatientId.getText().isBlank())
			patientEntity
					.setPatientId(Long.valueOf(searchPatientId.getText() != null && !searchPatientId.getText().isBlank()
							? searchPatientId.getText().toString()
							: null));
		patientEntity.setPatientName(searchPatientName.getText() != null && !searchPatientName.getText().isBlank()
				? searchPatientName.getText().toString()
				: null);

		patientsList = PatientClient.getPatientDetails(patientEntity);

		populatePatientDetails(patientsList);

	}

	public void populatePatientDetails(List<PatientDetailsSO> patientsList) {
		
		table.removeAll();
		
		
		

		for (PatientDetailsSO patient : patientsList) {
			TableItem item = new TableItem(table, SWT.NONE);
			item.setText(0, Long.toString(patient.getPatientId()));
			item.setText(1, patient.getPatientName());

			if (patient.getPatientDOB() != null) {
				DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
				String sDate = df.format(patient.getPatientDOB().getTime());
				item.setText(2, sDate);
			}

			item.setText(3, patient.getContactNumber());
			if(patient.getPatientAddresses() != null && patient.getPatientAddresses().size()>0) {
			item.setText(4, patient.getPatientAddresses().get(0).getAddressLine1()!=null ?patient.getPatientAddresses().get(0).getAddressLine1(): "" );
			item.setText(5, patient.getPatientAddresses().get(0).getState() != null ? patient.getPatientAddresses().get(0).getState() : "");
			item.setText(6, patient.getPatientAddresses().get(0).getPincode() != null ? patient.getPatientAddresses().get(0).getPincode() : "");
			}
		}

	}
}
