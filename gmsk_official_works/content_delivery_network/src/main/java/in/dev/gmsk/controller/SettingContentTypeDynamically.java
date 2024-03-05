package in.dev.gmsk.controller;

import java.io.InputStream;

import org.springframework.core.io.InputStreamResource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/dyn")
public class SettingContentTypeDynamically {

	/* http://localhost:8080/api/v1/dyn/get-image-dynamic-type?jpg=true */

	@GetMapping("/get-image-dynamic-type")
	@ResponseBody
	public ResponseEntity<InputStreamResource> getImageDynamicType(@RequestParam("jpg") boolean jpg) {
		MediaType contentType = jpg ? MediaType.IMAGE_JPEG : MediaType.IMAGE_PNG;
		InputStream in = jpg ? getClass().getResourceAsStream("/in/dev/gmsk/image/cerplogo.png")
				: getClass().getResourceAsStream("/in/dev/gmsk/svg/all_student_avatar.svg");
		return ResponseEntity.ok().contentType(contentType).body(new InputStreamResource(in));
	}
}
