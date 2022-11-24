/**
 * 
 */
package test.com.patient.httpclient;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.patient.model.AddressListSO;
import com.patient.model.PatientDetailsSO;
import com.patient.model.PatientIdentifierSO;

/**
 * @author KL105911
 *
 */
class PatientClientTest {
	
	private PatientDetailsSO patientDetailsSO;

	/**
	 * @throws java.lang.Exception
	 */
	@BeforeEach
	void setUp() throws Exception {
		
		patientDetailsSO = new PatientDetailsSO();

		patientDetailsSO.setPatientId(106);
		patientDetailsSO.setPatientName("Kavitha");
		patientDetailsSO.setContactNumber("9645422876");
		patientDetailsSO.setPatientDOB(new Date());
		patientDetailsSO.setAlternateContactNumber("9856872933");

		PatientIdentifierSO patientIdentifier = new PatientIdentifierSO("Aadhar Card", "131232142424214");

		List<PatientIdentifierSO> patientIdentifierList = new ArrayList<>();

		patientIdentifierList.add(patientIdentifier);
		patientDetailsSO.setPatientIdentifiers(patientIdentifierList);

		AddressListSO address = new AddressListSO(123,"Addr Line", "Addr Line2", "Kerala", "India", "680004","PRIMARY");

		List<AddressListSO> addressList = new ArrayList<>();

		addressList.add(address);
		patientDetailsSO.setPatientAddresses(addressList);
		
		
	}

	/**
	 * @throws java.lang.Exception
	 */
	@AfterEach
	void tearDown() throws Exception {
	}

	/**
	 * Test method for {@link com.patient.httpclient.PatientClient#savePatientDetails(com.patient.model.PatientDetailsSO)}.
	 * @throws InterruptedException 
	 * @throws IOException 
	 */
	@Test
	void testSavePatientDetails() throws IOException, InterruptedException {
		
		String savePatientUrl="http://localhost:8080/patient/savePatientDetails";
		ObjectMapper mapper = new ObjectMapper();
		String requestBody = mapper.writeValueAsString(patientDetailsSO);
		
		var request=HttpRequest.newBuilder()
				.POST(HttpRequest.BodyPublishers.ofString(requestBody))
				.uri(URI.create(savePatientUrl))
				.header("Content-Type", "application/json")
				.build();
		
		var client=HttpClient.newHttpClient();
		var response = client.send(request, HttpResponse.BodyHandlers.ofString());
		
		// Then
        assertEquals(response.statusCode(), 200);
        
        PatientDetailsSO savedPatientDetailsSO=mapper.readValue(response.body(), new TypeReference<PatientDetailsSO>() {});
        
        assertEquals(savedPatientDetailsSO.getStatusMsg(), "Patient Details Saved Successfully");
		
	}

	/**
	 * Test method for {@link com.patient.httpclient.PatientClient#getPatientDetails(com.patient.model.PatientDetailsSO)}.
	 * @throws InterruptedException 
	 * @throws IOException 
	 */
	@Test
	void testGetPatientDetails() throws IOException, InterruptedException {
		
		String savePatientUrl="http://localhost:8080/patient/savePatientDetails";
		ObjectMapper mapper = new ObjectMapper();
		String requestBody = mapper.writeValueAsString(patientDetailsSO);
		
		
		var request=HttpRequest.newBuilder()
				.POST(HttpRequest.BodyPublishers.ofString(requestBody))
				.uri(URI.create(savePatientUrl))
				.header("Content-Type", "application/json")
				.build();
		
		var client=HttpClient.newHttpClient();
		var response = client.send(request, HttpResponse.BodyHandlers.ofString());
		
     // Then
        assertEquals(response.statusCode(), 200);
	}

	/**
	 * Test method for {@link com.patient.httpclient.PatientClient#removePatientDetails(com.patient.model.PatientDetailsSO)}.
	 * @throws InterruptedException 
	 * @throws IOException 
	 */
	@Test
	void testRemovePatientDetails() throws IOException, InterruptedException {
		
		String updatePatientUrl="http://localhost:8080/patient/removePatientDetails";
		ObjectMapper mapper = new ObjectMapper();
		String requestBody = mapper.writeValueAsString(patientDetailsSO);
		
		var request=HttpRequest.newBuilder()
				.POST(HttpRequest.BodyPublishers.ofString(requestBody))
				.uri(URI.create(updatePatientUrl))
				.header("Content-Type", "application/json")
				.build();
		
		var client=HttpClient.newHttpClient();
		var response = client.send(request, HttpResponse.BodyHandlers.ofString());
		
		// Then
        assertEquals(response.statusCode(), 200);
        
        String savedPatientDetailsSO=mapper.writeValueAsString(response.body());
        
        String invalidMsg="Invalid Patient ID";
        
        assertEquals(savedPatientDetailsSO, '"'+invalidMsg+'"');
		
	}

	/**
	 * Test method for {@link com.patient.httpclient.PatientClient#updatePatientDetails(com.patient.model.Patient)}.
	 * @throws InterruptedException 
	 * @throws IOException 
	 */
	@Test
	void testUpdatePatientDetails() throws IOException, InterruptedException {
		String savePatientUrl="http://localhost:8080/patient/updatePatientDetails";
		ObjectMapper mapper = new ObjectMapper();
		String requestBody = mapper.writeValueAsString(patientDetailsSO);
		
		var request=HttpRequest.newBuilder()
				.POST(HttpRequest.BodyPublishers.ofString(requestBody))
				.uri(URI.create(savePatientUrl))
				.header("Content-Type", "application/json")
				.build();
		
		var client=HttpClient.newHttpClient();
		var response = client.send(request, HttpResponse.BodyHandlers.ofString());
		
		// Then
        assertEquals(response.statusCode(), 200);
        
        PatientDetailsSO savedPatientDetailsSO=mapper.readValue(response.body(), new TypeReference<PatientDetailsSO>() {});
        
        assertEquals(savedPatientDetailsSO.getStatusMsg(), null);
	}

}
