package in.dev.gmsk;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.*;

@SpringBootApplication
@RestController
@RequestMapping("/")
public class DeckerComposeDemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(DeckerComposeDemoApplication.class, args);
    }

    @GetMapping("/")
    public String sayGreetings() {
        return "Hello Lunar *)";
    }
}
