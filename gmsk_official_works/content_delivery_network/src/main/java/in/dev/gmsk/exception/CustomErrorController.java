package in.dev.gmsk.exception;

import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class CustomErrorController implements ErrorController {

	@GetMapping("/error")
	public ModelAndView errorPage() {
		ModelAndView mv = new ModelAndView();
		mv.setViewName("error");
		return mv;
	}
}
