package in.dev.gmsk;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@SpringBootApplication
@Configuration
public class ReferenceWorkApiApplication {

    public static void main(String[] args) {
        SpringApplication.run( ReferenceWorkApiApplication.class, args );
    }

    @Bean
    public WebMvcConfigurer corsConfigure()
    {
        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/api/**")
                        .allowedMethods( "GET" )
                        .allowedOrigins("http://localhost:3000/");
                     //   .allowCredentials(true).maxAge(3600);
            }
        };
    }

	/*  StaffRepo staffRepo = context.getBean( StaffRepo.class );
		Stream<Staff> staffPeriodDetails = staffRepo.getStaffPeriodDetails();
		System.out.println( "staffPeriodDetails = " + staffPeriodDetails.toList() );
	*/
}
