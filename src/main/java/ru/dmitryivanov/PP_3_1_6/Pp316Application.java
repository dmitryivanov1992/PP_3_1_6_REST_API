package ru.dmitryivanov.PP_3_1_6;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.web.client.RestTemplate;
import ru.dmitryivanov.PP_3_1_6.model.User;

import java.util.List;

@SpringBootApplication
public class Pp316Application {
	public static void main(String[] args) {
		SpringApplication.run(Pp316Application.class, args);
	}
}


