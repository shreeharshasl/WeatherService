package com.assess.WeatherService.service;

import com.assess.WeatherService.model.WeatherResponse;

public interface WeatherService {

	WeatherResponse fetchWeatherDataForCity(String city, Integer days);
}
