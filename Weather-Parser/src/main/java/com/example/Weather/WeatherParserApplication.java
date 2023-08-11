package com.example.Weather;

import com.example.Weather.Services.WeatherService;
import com.example.Weather.weather.WeatherFromSite;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
public class WeatherParserApplication {

	public static void main(String[] args) {
		SpringApplication.run(WeatherParserApplication.class, args);
	}

}
