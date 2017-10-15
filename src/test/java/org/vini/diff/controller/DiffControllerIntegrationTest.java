package org.vini.diff.controller;

import static org.junit.Assert.assertTrue;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.skyscreamer.jsonassert.JSONAssert;
import org.springframework.boot.context.embedded.LocalServerPort;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import org.vini.diff.DiffApiApp;
import org.vini.diff.model.EncodedBase64Data;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = DiffApiApp.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class DiffControllerIntegrationTest {

	@LocalServerPort
	private int port;

	TestRestTemplate restTemplate = new TestRestTemplate();

	HttpHeaders headers = new HttpHeaders();

	@Test
	public void testGetDiffOnlySizeEquals() {

		//left
		EncodedBase64Data encodedData = new EncodedBase64Data("dmluaWNpdXM=");
		HttpEntity<EncodedBase64Data> entity = new HttpEntity<EncodedBase64Data>(encodedData, headers);
		ResponseEntity<String> response = restTemplate.exchange(
				createURLWithPort("/v1/diff/123/left"),
				HttpMethod.POST, entity, String.class);
		assertTrue(HttpStatus.OK == response.getStatusCode());
		
		//right
		encodedData = new EncodedBase64Data("aXNhYmVsbGU=");
		entity = new HttpEntity<EncodedBase64Data>(encodedData, headers);
		response = restTemplate.exchange(
				createURLWithPort("/v1/diff/123/right"),
				HttpMethod.POST, entity, String.class);
		assertTrue(HttpStatus.OK == response.getStatusCode());
		
		//diff
		HttpEntity<String> entityDiff = new HttpEntity<String>(null, headers);
		ResponseEntity<String> responseDiff = restTemplate.exchange(
				createURLWithPort("/v1/diff/123"),
				HttpMethod.GET, entityDiff, String.class);
		String expected = "{\"id\":\"123\",\"equals\":false,\"equalSize\":true,\""
				+ "differences\":[{\"byteIndex\":0,\"numberOfDifferentBits\":5},"
				+ "{\"byteIndex\":1,\"numberOfDifferentBits\":3},{\"byteIndex\":2,"
				+ "\"numberOfDifferentBits\":4},{\"byteIndex\":3,\"numberOfDifferentBits\":3},"
				+ "{\"byteIndex\":4,\"numberOfDifferentBits\":2},{\"byteIndex\":5,"
				+ "\"numberOfDifferentBits\":2},{\"byteIndex\":6,\"numberOfDifferentBits\":3},"
				+ "{\"byteIndex\":7,\"numberOfDifferentBits\":3}]}";

		JSONAssert.assertEquals(expected, responseDiff.getBody(), false);
	}
	
	@Test
	public void testGetDiffEquals() {

		//right
		EncodedBase64Data encodedData = new EncodedBase64Data("dmluaWNpdXM=");
		HttpEntity<EncodedBase64Data> entity = new HttpEntity<EncodedBase64Data>(encodedData, headers);
		ResponseEntity<String> response = restTemplate.exchange(
				createURLWithPort("/v1/diff/1234/right"),
				HttpMethod.POST, entity, String.class);
		assertTrue(HttpStatus.OK == response.getStatusCode());
		
		//left
		encodedData = new EncodedBase64Data("dmluaWNpdXM=");
		entity = new HttpEntity<EncodedBase64Data>(encodedData, headers);
		response = restTemplate.exchange(
				createURLWithPort("/v1/diff/1234/left"),
				HttpMethod.POST, entity, String.class);
		assertTrue(HttpStatus.OK == response.getStatusCode());
		
		//diff
		HttpEntity<String> entityDiff = new HttpEntity<String>(null, headers);
		ResponseEntity<String> responseDiff = restTemplate.exchange(
				createURLWithPort("/v1/diff/1234"),
				HttpMethod.GET, entityDiff, String.class);
		String expected = "{\"id\":\"1234\",\"equals\":true,\"equalSize\":true,\"differences\":[]}";

		JSONAssert.assertEquals(expected, responseDiff.getBody(), false);
	}
	
	@Test
	public void testGetDiffNotEqualsDifferentSize() {

		//left
		EncodedBase64Data encodedData = new EncodedBase64Data("dmluaWNpdXM=");
		HttpEntity<EncodedBase64Data> entity = new HttpEntity<EncodedBase64Data>(encodedData, headers);
		ResponseEntity<String> response = restTemplate.exchange(
				createURLWithPort("/v1/diff/1234/left"),
				HttpMethod.POST, entity, String.class);
		assertTrue(HttpStatus.OK == response.getStatusCode());
		
		//right
		encodedData = new EncodedBase64Data("YW5hbmRh");
		entity = new HttpEntity<EncodedBase64Data>(encodedData, headers);
		response = restTemplate.exchange(
				createURLWithPort("/v1/diff/1234/right"),
				HttpMethod.POST, entity, String.class);
		assertTrue(HttpStatus.OK == response.getStatusCode());
		
		//diff
		HttpEntity<String> entityDiff = new HttpEntity<String>(null, headers);
		ResponseEntity<String> responseDiff = restTemplate.exchange(
				createURLWithPort("/v1/diff/1234"),
				HttpMethod.GET, entityDiff, String.class);
		String expected = "{\"id\":\"1234\",\"equals\":false,\"equalSize\":false,\"differences\":[]}";

		JSONAssert.assertEquals(expected, responseDiff.getBody(), false);
	}

	private String createURLWithPort(String uri) {
		return "http://localhost:" + port + uri;
	}

}
