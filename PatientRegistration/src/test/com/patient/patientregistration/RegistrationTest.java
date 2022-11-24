package test.com.patient.patientregistration;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swtbot.swt.finder.SWTBot;
import org.eclipse.swtbot.swt.finder.utils.SWTBotPreferences;
import org.eclipse.swtbot.swt.finder.widgets.SWTBotCombo;
import org.eclipse.swtbot.swt.finder.widgets.SWTBotLabel;
import org.eclipse.swtbot.swt.finder.widgets.SWTBotShell;
import org.eclipse.swtbot.swt.finder.widgets.SWTBotTable;
import org.eclipse.swtbot.swt.finder.widgets.SWTBotText;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.patient.patientregistration.PatientLocator;
import com.patient.patientregistration.Registration;

class RegistrationTest {
	
	public SWTBot bot;
	public SWTBot bot1;
	public Shell shell;
	public Shell shellLoc;
	public Display display;
	Registration reg;
	PatientLocator patientLocator;

	@BeforeEach
	void setUp() throws Exception {
		//display = new Display();
		shell = new Shell();
		shell.setSize(1250, 790);
		shell.setText("Patient Registration Form");
		
		reg=new Registration();
		reg.addFileds(shell, true, false, false);
		shell.open();
		bot = new SWTBot(shell);
		
		//display = new Display();
				shellLoc = new Shell();
				shellLoc.setSize(1250, 790);
				//shellLoc.setText("Patient Registration Form");
				
				patientLocator=new PatientLocator();
				patientLocator.addFileds(shellLoc);
				shellLoc.open();
				bot1 = new SWTBot(shellLoc);
	
	}

	@AfterEach
	void tearDown() throws Exception {
	}

	@Test
	void test() {
		SWTBotPreferences.PLAYBACK_DELAY = 100; // slow down the tests.
		SWTBotLabel botLabel = bot.label("Patient Registration Form");
		assertEquals("Patient Registration Form", botLabel.getText());
		assertTrue(bot.button("SUBMIT").isEnabled());
		
	}
	
	@Test
	void whenPatientNameProvidedAndContactNumBlank_SavePatient_thenAlertValidationError() {
		
		SWTBotText txt = bot.text(1);
		System.out.println(txt);

		// test for search with out button
		txt.setFocus();
		txt.setText("Kavitha");

		assertEquals("Kavitha", txt.getText());

		bot.button("SUBMIT").click();
		
		SWTBotShell alertShell = bot.shell("Alert");

		assertTrue(alertShell.isActive());
		alertShell.close();
	}
	
	@Test
	void whenContactNumberMissing_SavePatient_thenAlertMessage() {
		
		SWTBotText txt = bot.text(1);

		// test for search with out button
		txt.setFocus();
		txt.setText("Kavitha");
		
		SWTBotText txt1 = bot.text(2);
		txt1.setText("9645422387");

		assertEquals("Kavitha", txt.getText());

		bot.button("SUBMIT").click();
		
		SWTBotShell alertShell = bot.shell("Alert");

		assertTrue(alertShell.isActive());
		alertShell.close();
	}
	
	
	@Test
	void whenMandatoryFieldsAdded_SavePatient_thenSuccessMessage() {
		
		SWTBotText txt = bot.text(1);

		// test for search with out button
		txt.setFocus();
		txt.setText("Kavitha");
		
		SWTBotText txt1 = bot.text(2);
		txt1.setText("9645422387");
		
		SWTBotText txt2 = bot.text(5);
		txt2.setText("Addr Line1");
		
		SWTBotText txt3 = bot.text(6);
		txt3.setText("Addr Line2");
		
		SWTBotText txt4 = bot.text(7);
		txt4.setText("State");
		
		SWTBotText txt5 = bot.text(8);
		txt5.setText("Country");
		
		SWTBotText txt6 = bot.text(9);
		txt6.setText("Pincode");
		
		SWTBotCombo combo = bot.comboBox(0);
		combo.setText("AADHAR CARD");
		
		SWTBotText txt7 = bot.text(4);
		txt7.setText("234523452345");

		//assertEquals("Kavitha", txt.getText());

		bot.button("SUBMIT").click();
		
		SWTBotShell alertShell = bot.shell("Alert");
		alertShell.getText();

		assertTrue(alertShell.isActive());
		alertShell.close();
	}
	
	@Test
	void whenNoValueSelected_SearchPatient_thenButtonsDiabled() {
		SWTBotTable botTable=bot1.table(0);
		
		if(botTable.selectionCount()>0) {
			
			assertTrue(!bot1.button(1).isEnabled());
			assertTrue(!bot1.button(2).isEnabled());
			assertTrue(!bot1.button(3).isEnabled());
			
		}
		
	}
		

}
