package com.oxak.kursach;

import com.vaadin.flow.component.dependency.NpmPackage;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.web.servlet.error.ErrorMvcAutoConfiguration;

@NpmPackage(value = "lumo-css-framework", version = "^4.0.10")
@SpringBootApplication(exclude = ErrorMvcAutoConfiguration.class)
public class KursachApplication {

	public static void main(String[] args) {
		SpringApplication.run(KursachApplication.class, args);
	}

}
