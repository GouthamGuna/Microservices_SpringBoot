package in.dev.gmsk;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@SpringBootApplication
@RestController
@RequestMapping("/")
public class ContentDeliveryNetworkApplication {

	public static void main(String[] args) {
		SpringApplication.run(ContentDeliveryNetworkApplication.class, args);
	}

	@GetMapping
	public ModelAndView getHomePage() {
		ModelAndView mv = new ModelAndView();
		mv.setViewName("welcome");
		mv.getModel().put("data", " Lunar 🌙");
		return mv;
	}

}
