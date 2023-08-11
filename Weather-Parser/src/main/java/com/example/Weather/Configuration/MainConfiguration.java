package com.example.Weather.Configuration;

import com.example.Weather.Services.WeatherService;
import com.example.Weather.weather.WeatherFromSite;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.thymeleaf.templateresolver.ClassLoaderTemplateResolver;

@Configuration
@EnableJpaRepositories("com.example.Weather.Services")
@ComponentScan("com.example.Weather")
public class MainConfiguration implements WebMvcConfigurer {
    @Autowired
    private WeatherService weatherService;

    @Bean
    public CommandLineRunner commandLineRunner(ApplicationContext context){
        return args -> {

            WeatherFromSite weatherVolgaKanatnayaDoroga = new WeatherFromSite(weatherService,
                    "https://yandex.ru/pogoda/?lat=56.33122635&lon=44.05951691",
                    "Volga Kanatnaya Doroga");
            Thread runVolgaKanatnayaDoroga = new Thread(weatherVolgaKanatnayaDoroga);

            WeatherFromSite weatherOka = new WeatherFromSite(weatherService,
                    "https://yandex.ru/pogoda/?lat=56.30480576&lon=43.95690536",
                    "Oka");
            Thread runOka = new Thread(weatherOka);

            WeatherFromSite weatherGooseLake = new WeatherFromSite(weatherService,
                    "https://yandex.ru/pogoda/?lat=56.26896667&lon=44.12596893",
                    "Goose lake");
            Thread runGooseLake = new Thread(weatherGooseLake);

            WeatherFromSite weatherOkaIntel = new WeatherFromSite(weatherService,
                    "https://yandex.ru/pogoda/?lat=56.25907516&lon=43.96545029",
                    "Oka Intel");
            Thread runOkaIntel = new Thread(weatherOkaIntel);

            WeatherFromSite weatherVolgaStrelka = new WeatherFromSite(weatherService,
                    "https://yandex.ru/pogoda/?lat=56.25907516&lon=43.96545029",
                    "Volga Strelka");
            Thread runVolgaStrelka = new Thread(weatherVolgaStrelka);

            WeatherFromSite weatherSormovoLake = new WeatherFromSite(weatherService,
                    "https://yandex.ru/pogoda/?lat=56.34182739&lon=43.84908676",
                    "Sormovo Lake");
            Thread runSormovoLake = new Thread(weatherSormovoLake);

            WeatherFromSite weatherKosmicheskaya = new WeatherFromSite(weatherService,
                    "https://yandex.ru/pogoda/?lat=56.23280716&lon=43.81580734",
                    "Kosmicheskaya");
            Thread runKosmicheskaya = new Thread(weatherKosmicheskaya);

            runVolgaKanatnayaDoroga.start();
            runOka.start();
            runGooseLake.start();
            runOkaIntel.start();
            runVolgaStrelka.start();
            runSormovoLake.start();
            runKosmicheskaya.start();
        };
    }

    @Bean
    public ClassLoaderTemplateResolver thymeleafTemplateResolver() {
        ClassLoaderTemplateResolver templateResolver = new ClassLoaderTemplateResolver();
        templateResolver.setPrefix("templates/");
        templateResolver.setSuffix(".html");
        templateResolver.setTemplateMode("HTML");
        templateResolver.setCharacterEncoding("UTF-8");
        return templateResolver;
    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry
                .addResourceHandler("/static/**")
                .addResourceLocations("classpath:/static/");
    }
}
