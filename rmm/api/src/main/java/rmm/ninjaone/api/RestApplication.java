package rmm.ninjaone.api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;

@SpringBootApplication
public class RestApplication {
    public static void main(String[] args) {
		SpringApplication.run(RestApplication.class, args);
	}

	@Bean
	public MessageSource messageSource() {
		ReloadableResourceBundleMessageSource messageSource
		= new ReloadableResourceBundleMessageSource();
		
		messageSource.setBasename("classpath:messages");
		messageSource.setDefaultEncoding("UTF-8");
		
		return messageSource;
	}
}