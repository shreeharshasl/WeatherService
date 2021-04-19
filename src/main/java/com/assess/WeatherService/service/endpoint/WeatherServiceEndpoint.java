package com.assess.WeatherService.service.endpoint;

import com.assess.WeatherService.external.models.OpenWeatherResponse;

public interface WeatherServiceEndpoint {
	
	OpenWeatherResponse getWeatherServiceFromOpenWeather(String city);

}
