package in.dev.gmsk.controller;

import java.io.IOException;
import java.io.InputStream;

import org.apache.commons.io.IOUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/svg")
public class ServingStaticSVGFiles {
	
	@GetMapping(value = "/all-student-avatar")
	public @ResponseBody byte[] getAllStudentAvatar() throws IOException {
		InputStream in = getClass().getResourceAsStream("/in/dev/gmsk/svg/all_student_avatar.svg");
		return IOUtils.toByteArray(in);
	}
}
