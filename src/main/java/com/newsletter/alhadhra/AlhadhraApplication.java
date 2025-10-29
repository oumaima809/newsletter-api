package com.newsletter.alhadhra;

import com.newsletter.alhadhra.config.AppProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@SpringBootApplication
@EnableConfigurationProperties({AppProperties.class})

public class AlhadhraApplication {

	public static void main(String[] args) {
		SpringApplication.run(AlhadhraApplication.class, args);
	}

}
