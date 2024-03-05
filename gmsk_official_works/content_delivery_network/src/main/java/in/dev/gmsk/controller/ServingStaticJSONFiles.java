package in.dev.gmsk.controller;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.io.IOUtils;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/data")
public class ServingStaticJSONFiles {

	@GetMapping(value = "/languages", produces = "application/json")
	public @ResponseBody byte[] getLanguages() throws IOException {
		InputStream in = getClass().getResourceAsStream("/in/dev/gmsk/json/languages.json");
		return IOUtils.toByteArray(in);
	}

	@GetMapping(value = "/inventory-data", produces = "application/json")
	public @ResponseBody byte[] getInventoryData() throws IOException {
		InputStream in = getClass().getResourceAsStream("/in/dev/gmsk/json/inventory_data.json");
		return IOUtils.toByteArray(in);
	}

	/* Demo Code Starting Here */

	@GetMapping(value = "/greetings-with-response-body", produces = "application/json")
	public String getGreetingWhileReturnTypeIsString() {
		return "{\"test\": \"Hello Lunar 🌙\"}";
	}

	@GetMapping(value = "/greetings-with-response-entity", produces = "application/json")
	public ResponseEntity<String> getGreetingWithResponseEntity() {
		final HttpHeaders httpHeaders = new HttpHeaders();
		httpHeaders.setContentType(MediaType.APPLICATION_JSON);
		return new ResponseEntity<>("{\"test\": \"Hello with ResponseEntity\"}", httpHeaders, HttpStatus.OK);
	}

	@GetMapping(value = "/greetings-with-map-return-type", produces = "application/json")
	public Map<String, Object> getGreetingWhileReturnTypeIsMap() {
		HashMap<String, Object> map = new HashMap<>();
		map.put("test", "Hello from map");
		return map;
	}

	/* Demo Code Ending Here */

}
