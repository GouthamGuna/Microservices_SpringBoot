package in.dev.gmsk.controller;

import java.io.IOException;
import java.io.InputStream;

import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/image")
public class ServingStaticImageFiles {

	private static final Logger LOGGER = LoggerFactory.getLogger(ServingStaticImageFiles.class);

	@Value("${spring.application.name}")
	private String applicationName;

	@GetMapping(value = "/cerpsoft-logo", produces = MediaType.IMAGE_JPEG_VALUE)
	public @ResponseBody byte[] getCerpsoftLogo() throws IOException {
		LOGGER.info("Request at {} for request getCerpsoftLogo.", applicationName);
		InputStream in = getClass().getResourceAsStream("/in/dev/gmsk/image/cerplogo.png");
		return IOUtils.toByteArray(in);
	}

	@GetMapping(value = "/500-error", produces = MediaType.IMAGE_JPEG_VALUE)
	public @ResponseBody byte[] getInternalServerError() throws IOException {
		LOGGER.info("Request at {} for request getInternalServerError.", applicationName);
		InputStream in = getClass().getResourceAsStream("/in/dev/gmsk/image/500-internal-server-error.jpg");
		return IOUtils.toByteArray(in);
	}

	@GetMapping(value = "/504-error", produces = MediaType.IMAGE_JPEG_VALUE)
	public @ResponseBody byte[] gatewayTimeout() throws IOException {
		LOGGER.info("Request at {} for request gatewayTimeout.", applicationName);
		InputStream in = getClass().getResourceAsStream("/in/dev/gmsk/image/504_gateway_timeout.jpg");
		return IOUtils.toByteArray(in);
	}

	/*
	 * Just one byte of data is returned by this procedure. commencing right here.!
	 */

	@GetMapping(value = "/image")
	public @ResponseBody byte[] getImage() throws IOException {
		LOGGER.info("Request at {} for request getImage.", applicationName);
		InputStream in = getClass().getResourceAsStream("/in/dev/gmsk/image/cerplogo.png");
		return IOUtils.toByteArray(in);
	}

	/*
	 * Just one byte of data is returned by this procedure. commencing ending here.!
	 */
}
