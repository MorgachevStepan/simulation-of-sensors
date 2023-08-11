package com.example.Weather.Services;

import com.example.Weather.weather.WeatherEntity;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;

@Service
public class WeatherService {
    @PersistenceContext
    private EntityManager entityManager;
    private final WeatherRepository weatherRepository;

    @Autowired
    public WeatherService(WeatherRepository weatherRepository) {
        this.weatherRepository = weatherRepository;
    }

    public void saveWeather(WeatherEntity weatherEntity){
        WeatherEntity weather = new WeatherEntity();
        weather.setId(weatherEntity.getId());
        weather.setLocation(weatherEntity.getLocation());
        weather.setWeatherEvent(weatherEntity.getWeatherEvent());
        weather.setUpdatedDate(weatherEntity.getUpdatedDate());
        weather.setTemperature(weatherEntity.getTemperature());
        weather.setWindSpeed(weatherEntity.getWindSpeed());
        weatherRepository.save(weather);
    }

    public List<WeatherEntity> list(){
        return weatherRepository.findAll();
    }

    public Optional<WeatherEntity> getByLocationLast(String location){
        Optional<WeatherEntity> weatherEntity = weatherRepository.findAll().stream()
                .filter(WeatherEntity -> WeatherEntity.getLocation().equals(location))
                .max(Comparator.comparing(WeatherEntity::getUpdatedDate));

        return weatherEntity;
    }

    public Optional<Integer> getMaxTemperatureByLocation(String location){
        Optional<WeatherEntity> weatherEntity = weatherRepository.findAll().stream()
                .filter(WeatherEntity -> WeatherEntity.getLocation().equals(location))
                .max(Comparator.comparing(WeatherEntity::getTemperature));

        Integer result = null;
        if(weatherEntity.isPresent()){
            WeatherEntity weatherEntity1 = weatherEntity.get();
            result = weatherEntity1.getTemperature();
        }
        return Optional.of(result);
    }

    public Optional<Integer> getMinTemperatureByLocation(String location){
        Optional<WeatherEntity> weatherEntity = weatherRepository.findAll().stream()
                .filter(WeatherEntity -> WeatherEntity.getLocation().equals(location))
                .min(Comparator.comparing(WeatherEntity::getTemperature));

        Integer result = null;
        if(weatherEntity.isPresent()){
            WeatherEntity weatherEntity1 = weatherEntity.get();
            result = weatherEntity1.getTemperature();
        }
        return Optional.of(result);
    }

    public Optional<String> getMinWindSpeedByLocation(String location){
        Optional<WeatherEntity> weatherEntity = weatherRepository.findAll().stream()
                .filter(WeatherEntity -> WeatherEntity.getLocation().equals(location))
                .min(Comparator.comparing(WeatherEntity::getWindSpeed));

        String result = null;
        if(weatherEntity.isPresent()){
            WeatherEntity weatherEntity1 = weatherEntity.get();
            result = weatherEntity1.getWindSpeed();
        }
        return Optional.of(result);
    }

    public Optional<String> getMaxWindSpeedByLocation(String location){
        Optional<WeatherEntity> weatherEntity = weatherRepository.findAll().stream()
                .filter(WeatherEntity -> WeatherEntity.getLocation().equals(location))
                .max(Comparator.comparing(WeatherEntity::getWindSpeed));

        String result = null;
        if(weatherEntity.isPresent()){
            WeatherEntity weatherEntity1 = weatherEntity.get();
            result = weatherEntity1.getWindSpeed();
        }
        return Optional.of(result);
    }

    public Optional<String> getBestPlaceByTemperature(){
        Optional<WeatherEntity> weatherEntity;
        weatherEntity = weatherRepository.findAll().stream()
                .sorted(Comparator.comparing(WeatherEntity::getId))
                .skip(weatherRepository.findAll().size() - 7).max(Comparator.comparing(WeatherEntity::getGoodness));

        return Optional.of(weatherEntity.get().getLocation());
    }
}
