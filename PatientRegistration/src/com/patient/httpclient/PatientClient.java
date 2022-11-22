package com.patient.httpclient;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.List;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.patient.model.PatientDetailsSO;

public class PatientClient {
	
	
	public static PatientDetailsSO savePatientDetails(PatientDetailsSO patientSO) throws IOException, InterruptedException{
		
		String savePatientUrl="http://localhost:8080/patient/savePatientDetails";
		ObjectMapper mapper = new ObjectMapper();
		String requestBody = mapper.writeValueAsString(patientSO);
		
		var request=HttpRequest.newBuilder()
				.POST(HttpRequest.BodyPublishers.ofString(requestBody))
				.uri(URI.create(savePatientUrl))
				.header("Content-Type", "application/json")
				.build();
		
		var client=HttpClient.newHttpClient();
		
		var response = client.send(request, HttpResponse.BodyHandlers.ofString());
		
		System.out.println(response.statusCode());
		System.out.println(response.body());
		
		mapper = new ObjectMapper();
		
		
		
		return mapper.readValue(response.body(), new TypeReference<PatientDetailsSO>() {});
	}
	
public static List<PatientDetailsSO> getPatientDetails(PatientDetailsSO patientSO) throws IOException, InterruptedException{
		
		String getPatientUrl="http://localhost:8080/patient/getPatientDetails";
		ObjectMapper mapper = new ObjectMapper();
		String requestBody = mapper.writeValueAsString(patientSO);
		
		var request=HttpRequest.newBuilder()
				.POST(HttpRequest.BodyPublishers.ofString(requestBody))
				.uri(URI.create(getPatientUrl))
				.header("Content-Type", "application/json")
				.build();
		
		var client=HttpClient.newHttpClient();
		
		var response = client.send(request, HttpResponse.BodyHandlers.ofString());
		
		System.out.println(response.statusCode());
		System.out.println(response.body());
		
		mapper = new ObjectMapper();
		
		
		
		return mapper.readValue(response.body(), new TypeReference<List<PatientDetailsSO>>() {});
	}


public static String removePatientDetails(PatientDetailsSO patientSO) throws IOException, InterruptedException{
	
	String removePatientUrl="http://localhost:8080/patient/removePatientDetails";
	ObjectMapper mapper = new ObjectMapper();
	String requestBody = mapper.writeValueAsString(patientSO);
	
	var request=HttpRequest.newBuilder()
			.POST(HttpRequest.BodyPublishers.ofString(requestBody))
			.uri(URI.create(removePatientUrl))
			.header("Content-Type", "application/json")
			.build();
	
	var client=HttpClient.newHttpClient();
	
	var response = client.send(request, HttpResponse.BodyHandlers.ofString());
	
	System.out.println(response.statusCode());
	System.out.println(response.body());
	
	mapper = new ObjectMapper();
	
	
	return mapper.writeValueAsString(response.body());
}

public static PatientDetailsSO updatePatientDetails(PatientDetailsSO patientSO) throws IOException, InterruptedException{
	
	String savePatientUrl="http://localhost:8080/patient/updatePatientDetails";
	ObjectMapper mapper = new ObjectMapper();
	String requestBody = mapper.writeValueAsString(patientSO);
	
	var request=HttpRequest.newBuilder()
			.POST(HttpRequest.BodyPublishers.ofString(requestBody))
			.uri(URI.create(savePatientUrl))
			.header("Content-Type", "application/json")
			.build();
	
	var client=HttpClient.newHttpClient();
	
	var response = client.send(request, HttpResponse.BodyHandlers.ofString());
	
	System.out.println(response.statusCode());
	System.out.println(response.body());
	
	mapper = new ObjectMapper();
	
	
	
	return mapper.readValue(response.body(), new TypeReference<PatientDetailsSO>() {});
}

}
