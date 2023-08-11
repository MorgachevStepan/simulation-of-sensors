package com.example.Weather.weather;

import com.example.Weather.Services.WeatherRepository;
import com.example.Weather.Services.WeatherService;
import org.hibernate.SessionFactory;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import java.io.IOException;
import java.net.URL;
import java.util.Date;

public class WeatherFromSite implements Runnable {
    private final WeatherService weatherService;
    private final Document page;
    private final String namePlace;
    private final WeatherEntity weatherEntity;

    public WeatherFromSite(WeatherService weatherService, String url, String namePlace) throws IOException {
        this.weatherService = weatherService;
        this.page = Jsoup.parse(new URL(url), 50000);
        this.namePlace = namePlace;
        this.weatherEntity = new WeatherEntity();
    }

    @Override
    public void run() {
        while (true){
            try {
                Element time = page.select("div[class=fact__temp-wrap]").first();
                Element currentWeatherEl = time.select("span[class=temp__value temp__value_with-unit]").first();
                String currentWeatherStr = currentWeatherEl.text();

                Element windSpeed = page.select("div[class=term term_orient_v fact__wind-speed]").first();
                Element currentWindSpeed = windSpeed.select("span[class=wind-speed]").first();
                String currentWindSpeedStr = currentWindSpeed.text();

                Element weatherEvent = page.select("div[class=link__condition day-anchor i-bem]").first();
                String weatherEventStr = weatherEvent.text();

                Date date = new Date();

                weatherEntity.setLocation(namePlace);
                weatherEntity.setTemperature(Integer.parseInt(currentWeatherStr));
                weatherEntity.setUpdatedDate(date);
                weatherEntity.setWindSpeed(currentWindSpeedStr);
                weatherEntity.setWeatherEvent(weatherEventStr);
                weatherService.saveWeather(weatherEntity);
                System.out.println(weatherEntity);

                Thread.sleep(60000);
            }
            catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }
}

