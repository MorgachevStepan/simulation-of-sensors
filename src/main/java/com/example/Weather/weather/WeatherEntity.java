package com.example.Weather.weather;

import jakarta.persistence.*;
import lombok.Data;
import org.springframework.stereotype.Component;

import java.util.Date;

@Data
@Entity
@Table(name = "weather", schema = "public")
@Component
public class WeatherEntity {
    @Id
    @SequenceGenerator(name = "user_seq", sequenceName = "user_user_id_seq")
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "weather_id")
    private Long id;
    @Column(name = "location")
    private String location;
    @Column(name = "temperature")
    private Integer temperature;
    @Column(name = "wind_speed")
    private String windSpeed;
    @Column(name = "weather_event")
    private String weatherEvent;
    @Column(name = "date")
    private Date updatedDate;

    public double getGoodness(){
        return temperature - Double.parseDouble(windSpeed.replace(',', '.')) - calculateWeatherEventPriority();
    }

    private int calculateWeatherEventPriority() {
        return switch (weatherEvent) {
            case ("Ясно") -> 0;
            case ("Облачно с прояснениями"), ("Малооблачно") -> 1;
            case ("Пасмурно") -> 3;
            case ("Небольшой дождь"), ("Слабый дождь") -> 5;
            case ("Дождь") -> 10;
            default -> 0;
        };
    }
}
