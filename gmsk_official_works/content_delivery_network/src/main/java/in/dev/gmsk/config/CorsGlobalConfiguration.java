package in.dev.gmsk.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@EnableWebMvc
public class CorsGlobalConfiguration implements WebMvcConfigurer {

	@Override
	public void addCorsMappings(CorsRegistry registry) {
		registry.addMapping("/**");
	}
	
	/*
	 * @Bean CorsWebFilter corsWebFilter() { CorsConfiguration corsConfig = new
	 * CorsConfiguration();
	 * corsConfig.setAllowedOrigins(Arrays.asList("http://allowed-origin.com"));
	 * corsConfig.setMaxAge(8000L); corsConfig.addAllowedMethod("GET");
	 * corsConfig.addAllowedHeader("GMSK-Allowed");
	 * 
	 * UrlBasedCorsConfigurationSource source = new
	 * UrlBasedCorsConfigurationSource(new PathPatternParser());
	 * source.registerCorsConfiguration("/api/**", corsConfig);
	 * 
	 * return new CorsWebFilter(source); }
	 */
}
