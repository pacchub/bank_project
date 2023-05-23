package com.bank.documentmanager.utils;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;

import com.bank.documentmanager.exception.RestExecutionException;
import com.fasterxml.jackson.databind.ObjectMapper;

@Component
public class RestUtils {
	
	public String sendGetRequest(String url) throws RestExecutionException {
		try {
	        URL obj = new URL(url);
			HttpURLConnection con = (HttpURLConnection) obj.openConnection();
			con.setRequestMethod(HttpMethod.GET.name());
			int responseCode = con.getResponseCode();
			
			if (responseCode == HttpURLConnection.HTTP_OK) { 
				BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
				String inputLine;
				StringBuilder response = new StringBuilder();
				while ((inputLine = in.readLine()) != null) {
					response.append(inputLine);
				}
				in.close();
				return response.toString();
			} else {
				throw new RestExecutionException("GET operation on " + url + " has failed with response code " + responseCode);
			}
		}
		catch(Exception ex) {
			throw new RestExecutionException("Error while performing GET operation on " + url + " : " + ex.getMessage());
		}
	}
	
	public String sendPostRequest(String url, Object payload) throws RestExecutionException {
		
		try {
	        ObjectMapper objectMapper = new ObjectMapper();
	        String requestBody;
			requestBody = objectMapper.writeValueAsString(payload);
	        
			URL obj = new URL(url);
			HttpURLConnection con = (HttpURLConnection) obj.openConnection();
			con.setRequestMethod(HttpMethod.POST.name());
			con.setRequestProperty(HttpHeaders.CONTENT_TYPE, "application/json; charset=UTF-8");
			con.setRequestProperty(HttpHeaders.ACCEPT, MediaType.APPLICATION_JSON_VALUE);
			con.setDoOutput(true);
			
			OutputStream os = con.getOutputStream();
			os.write(requestBody.getBytes(StandardCharsets.UTF_8));
			os.flush();
			os.close();
	
			int responseCode = con.getResponseCode();
			
			if (responseCode == HttpURLConnection.HTTP_CREATED) { 
				BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
				String inputLine;
				StringBuilder response = new StringBuilder();
				while ((inputLine = in.readLine()) != null) {
					response.append(inputLine);
				}
				in.close();
				return response.toString();
			} else {
				throw new RestExecutionException("POST operation on " + url + " has failed with response code " + responseCode);
			}
		}
		catch(Exception ex) {
			throw new RestExecutionException("Error while performing POST operation on " + url + " : " + ex.getMessage());
		}
	}

}
