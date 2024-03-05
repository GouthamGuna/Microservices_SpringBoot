package in.dev.gmsk.controller;

import java.io.IOException;
import java.io.InputStream;

import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/js")
public class ServingStaticJSFiles {
	private static final Logger LOGGER = LoggerFactory.getLogger(ServingStaticJSFiles.class);
	
	@Value("${spring.application.name}")
	private String applicationName;
	
	@GetMapping(value = "/index.js", produces = "application/x-javascript")
	public @ResponseBody byte[] getIndexJSTesting() throws IOException {
		LOGGER.info("Request at {} for request getIndexJSTesting.", applicationName);
		InputStream in = getClass().getResourceAsStream("/in/dev/gmsk/js/index.js");
		return IOUtils.toByteArray(in);
	}

	@GetMapping(value = "/angular-min.js", produces = "application/x-javascript")
	public @ResponseBody byte[] getAngularMin() throws IOException {
		LOGGER.info("Request at {} for request getAngularMin.", applicationName);
		InputStream in = getClass().getResourceAsStream("/in/dev/gmsk/js/angular.min.js");
		return IOUtils.toByteArray(in);
	}

	@GetMapping(value = "/angular-route.js", produces = "application/x-javascript")
	public @ResponseBody byte[] getAngularRoute() throws IOException {
		InputStream in = getClass().getResourceAsStream("/in/dev/gmsk/js/angular-route.js");
		return IOUtils.toByteArray(in);
	}

	@GetMapping(value = "/app.js", produces = "application/x-javascript")
	public @ResponseBody byte[] getApp() throws IOException {
		InputStream in = getClass().getResourceAsStream("/in/dev/gmsk/js/app.js");
		return IOUtils.toByteArray(in);
	}

	@GetMapping(value = "/zp-api.js", produces = "application/x-javascript")
	public @ResponseBody byte[] getZP_API_JS() throws IOException {
		InputStream in = getClass().getResourceAsStream("/in/dev/gmsk/js/zp_api.js");
		return IOUtils.toByteArray(in);
	}

}
