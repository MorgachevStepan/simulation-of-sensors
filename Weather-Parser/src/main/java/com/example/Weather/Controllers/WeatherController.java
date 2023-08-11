package com.example.Weather.Controllers;

import com.example.Weather.Services.WeatherService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/weather")
public class WeatherController {
    private final WeatherService weatherService;

    public WeatherController(WeatherService weatherService) {
        this.weatherService = weatherService;
    }

    @GetMapping("/list")
    public String getList(Model model) {
        model.addAttribute("weathers", weatherService.list());
        return "weather-list";
    }

    @GetMapping("/{location}/current")
    public String getByLocationLast(@PathVariable String location, Model model) {
        model.addAttribute("weather", weatherService.getByLocationLast(location).orElse(null));
        return "weather-current";
    }

    @GetMapping("/{location}/maxTemperature")
    public String getMaxTemperature(@PathVariable String location, Model model) {
        model.addAttribute("maxTemperature", weatherService.getMaxTemperatureByLocation(location).orElse(null));
        model.addAttribute("location", location);
        return "max-temperature";
    }

    @GetMapping("/{location}/minTemperature")
    public String getMinTemperature(@PathVariable String location, Model model) {
        model.addAttribute("minTemperature", weatherService.getMinTemperatureByLocation(location).orElse(null));
        model.addAttribute("location", location);
        return "min-temperature";
    }

    @GetMapping("/{location}/maxWindSpeed")
    public String getMaxWindSpeed(@PathVariable String location, Model model) {
        model.addAttribute("maxWindSpeed", weatherService.getMaxWindSpeedByLocation(location).orElse(null));
        model.addAttribute("location", location);
        return "max-wind-speed.html";
    }

    @GetMapping("/{location}/minWindSpeed")
    public String getMinWindSpeed(@PathVariable String location, Model model) {
        model.addAttribute("minWindSpeed", weatherService.getMinWindSpeedByLocation(location).orElse(null));
        model.addAttribute("location", location);
        return "min-wind-speed";
    }

    @GetMapping("best")
    public String getBestPlace(Model model) {
        model.addAttribute("bestPlace", weatherService.getBestPlaceByTemperature().orElse(null));
        return "best-place";
    }
}